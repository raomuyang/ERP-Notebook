package org.jufe.erp.service.about.impl;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.jufe.erp.entity.MShow;
import org.jufe.erp.repository.about.MShowRepository;
import org.jufe.erp.service.about.MShowService;
import org.jufe.erp.utils.DateTool;
import org.jufe.erp.utils.FileUtil;
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

    private Logger logger = Logger.getLogger(MShowServiceImpl.class);

    @Override
    public MShow getMShow() {
        return mShowRepository.getMShow();
    }

    @Override
    public boolean update(MShow mShow) {
        return mShowRepository.update(mShow);
    }

    @Override
    public boolean updateIurls(List<String> iurls) {

        //更新urls时一并更新历史链接
        MShow mShow = getMShow();
        List<String> pushList = new ArrayList<>();
        for (String url: iurls)
            if(mShow.getiHistory().contains(url))
                pushList.add(url);

        mShowRepository.getMongoTemplate().updateFirst(
                new Query(new Criteria("id").is(MShowRepository.id)),
                new Update().push("iHistory", pushList), MShow.class
        );

        return mShowRepository.updateIurls(iurls);
    }

    @Override
    public boolean updateVurl(String vurl) {
        MShow mShow = getMShow();
        if( !mShow.getvHistory().contains( vurl ) )
            mShowRepository.getMongoTemplate().updateFirst(
                    new Query(new Criteria("id").is(MShowRepository.id)),
                    new Update().push("vHistory", vurl), MShow.class);
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
    public String uploadImage(MultipartFile multipartFile, String rootPath) {
        try {
            String subpath = ResourceEnum.IMAGE.p() + "/"
                    + DateTool.dateFormat(new Date(System.currentTimeMillis()), "yyyyMMdd");
            String fileId = new ObjectId().toString();
            String path = rootPath + "/" + subpath;

            String originalFileName = multipartFile.getOriginalFilename();
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            String filename = fileId + suffix;

            boolean res = FileUtil.writeFile(path, filename, multipartFile.getBytes());
            if(res)
                return "/" + subpath + "/" + filename;
        } catch (IOException e) {
            logger.error("Upload Image:" + e);
        }
        return null;
    }

    @Override
    public String uploadVideo(MultipartFile multipartFile, String rootPath) {
        try {
            String subpath = ResourceEnum.VIDEO.p() + "/"
                    + DateTool.dateFormat(new Date(System.currentTimeMillis()), "yyyyMMdd");
            String fileId = new ObjectId().toString();
            String path = rootPath + "/" + subpath;

            String originalFileName = multipartFile.getOriginalFilename();
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            String filename = fileId + suffix;

            boolean res = FileUtil.writeFile(path, filename, multipartFile.getBytes());
            if(res)
                return "/" + subpath + "/" + filename;
        } catch (IOException e) {
            logger.error("Upload Video:" + e);
        }
        return null;
    }
}
