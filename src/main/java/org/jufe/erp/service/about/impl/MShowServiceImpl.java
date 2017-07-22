package org.jufe.erp.service.about.impl;

import org.apache.log4j.Logger;
import org.jufe.erp.entity.MShow;
import org.jufe.erp.component.QOSComponent;
import org.jufe.erp.repository.about.MShowRepository;
import org.jufe.erp.service.about.MShowService;
import org.jufe.erp.utils.DateTools;
import org.jufe.erp.utils.MultipartFileSave;
import org.jufe.erp.utils.enums.ResourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Raomengnan on 2016/9/1.
 */
@Service
public class MShowServiceImpl implements MShowService{

    @Autowired
    private MShowRepository mShowRepository;

    @Autowired
    private QOSComponent qosComponent;

    private Logger logger = Logger.getLogger(MShowServiceImpl.class);

    @Override
    public MShow getMShow() {
        return mShowRepository.getMShow();
    }

    @Override
    public boolean update(MShow mShow) {
        if(mShow == null)
            return false;
        /**
         * 更新历史url列表
         */
        MShow mShowCurrent = getMShow();
        if(mShowCurrent == null)
            mShowCurrent = new MShow();

        List<String> iHistory = mShowCurrent.getiHistory();
        if(iHistory == null)
            iHistory = new ArrayList<>();
        for (String url: mShow.getIurls())
            if(!(iHistory.contains(url)))
                iHistory.add(url);

        List<String> vHistory = mShowCurrent.getvHistory();
        if(vHistory == null)
            vHistory = new ArrayList<>();
        if(!vHistory.contains(mShow.getVurl()))
            vHistory.add(mShow.getVurl());

        mShow.setiHistory(iHistory);
        mShow.setvHistory(vHistory);

        return mShowRepository.update(mShow);
    }

    @Override
    public boolean updateIurls(List<String> iurls) {

        //更新urls时一并更新历史链接
        MShow mShow = getMShow();
        List<String> iList = createNewIHistoryList(iurls, mShow);

        if (mShow == null) {
            mShow = new MShow();
            mShow.setiHistory(iList);
            mShow.setIurls(iurls);
            return mShowRepository.update(mShow);
        }

        mShowRepository.getMongoTemplate().updateFirst(
                    new Query(new Criteria("id").is(MShowRepository.id)),
                    new Update().set("iHistory", iList), MShow.class);

        return mShowRepository.updateIurls(iurls);
    }

    @Override
    public boolean updateVurl(String vurl) {
        MShow mShow = getMShow();
        List<String> vHistory = createNewVHistoryList(vurl, mShow);

        if(mShow == null){
            mShow = new MShow();
            mShow.setvHistory(vHistory);
            mShow.setVurl(vurl);
            return mShowRepository.update(mShow);
        }

        mShowRepository.getMongoTemplate().updateFirst(
                new Query(new Criteria("id").is(MShowRepository.id)),
                new Update().set("vHistory", vurl), MShow.class);
        return mShowRepository.updateVurl(vurl);
    }

    @Override
    public boolean updateWords(String words) {
        return mShowRepository.updateWords(words);
    }

    @Override
    public boolean updateIHistory(List<String> iHistory) {
        return mShowRepository.updateIHistory(iHistory);
    }

    @Override
    public boolean updateVHistory(List<String> vHistory) {
        return mShowRepository.updateVHistory(vHistory);
    }

    @Override
    public boolean deleteImages(List<String> urls) {
        if(urls == null )
            return false;
        MShow mShow = getMShow();
        if(mShow == null)
            return false;

        List<String> imageList = mShow.getiHistory();
        if(imageList == null)
            return false;
        int len = imageList.size();
        urls.forEach(u->{
            try {
                boolean delF = qosComponent.getQos().deleteFile(u);
                boolean remU = imageList.remove(u);
                logger.info(String.format("Delete[%s]: %s; Remove url[%s]: %s",
                         u, delF, u, remU));
            }catch (Exception e){
                logger.error("delete image:" + e);
            }
        });

        if(imageList.size() == len)
            return false;

        return updateIHistory(imageList);
    }

    @Override
    public boolean deleteVideos(List<String> urls) {
        if(urls == null )
            return false;
        MShow mShow = getMShow();
        if(mShow == null)
            return false;

        List<String> videoList = mShow.getvHistory();
        int len = videoList.size();
        if(videoList == null)
            return false;
        urls.forEach(u->{
            try {
                boolean delF = qosComponent.getQos().deleteFile(u);
                boolean remU = videoList.remove(u);
                logger.info(String.format("Delete[%s]: %s; Remove url[%s]: %s",
                         u, delF, u, remU));
            }catch (Exception e){
                logger.error("delete video:" + e);
            }
        });
        if(videoList.size() == len)
            return false;
        return updateVHistory(videoList);
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        try {

            String url = MultipartFileSave.save2Qiniu(qosComponent.getQos(), multipartFile, ResourceEnum.IMAGE, null);

            final String subpath = ResourceEnum.IMAGE.p() + "/"
                    + DateTools.dateFormat(new Date(), "yyyyMMdd");

            if(url != null) {
                //将新的图片url加到历史【图库】中
                List iurls = new ArrayList();
                iurls.add(url);
                List<String> iHistory = createNewIHistoryList(iurls, getMShow());
                updateIHistory(iHistory);

                return url;
            }
        } catch (IOException e) {
            logger.error("Upload Image:" + e);
        }
        return null;
    }

    @Override
    public String uploadVideo(MultipartFile multipartFile) {
        try {

            String url = MultipartFileSave.save2Qiniu(qosComponent.getQos(), multipartFile, ResourceEnum.VIDEO, null);

            if(url != null) {
                //将新的视频url加到历史【视频库】中

                List<String> vHistory = createNewVHistoryList(url, getMShow());
                updateVHistory(vHistory);

                return url;
            }
        } catch (IOException e) {
            logger.error("Upload Video:" + e);
        }
        return null;
    }

    private List<String> createNewIHistoryList(List<String> iurls, MShow mShow){
        if(iurls == null || iurls.size() == 0)
            return null;

        List<String> iList = null;
        if(mShow == null || (iList = mShow.getiHistory()) == null)
            iList = new ArrayList<>();

        for (String url: iurls)
            if( !iList.contains(url))
                iList.add(url);

        return iList;
    }

    private List<String> createNewVHistoryList(String vurl, MShow mShow){
        List<String> vHistoryList = null;
        if(mShow != null )
            vHistoryList = mShow.getvHistory();
        if(vHistoryList == null)
            vHistoryList = new ArrayList<>();

        if(!vHistoryList.contains( vurl ) )
            vHistoryList.add(vurl);
        return vHistoryList;
    }

}
