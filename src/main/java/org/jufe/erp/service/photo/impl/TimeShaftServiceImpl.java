package org.jufe.erp.service.photo.impl;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.jufe.erp.entity.TimeShaft;
import org.jufe.erp.repository.Page;
import org.jufe.erp.repository.photo.TimeShaftRepository;
import org.jufe.erp.service.photo.TimeShaftService;
import org.jufe.erp.utils.DateTools;
import org.jufe.erp.utils.FileUtils;
import org.jufe.erp.utils.enums.ResourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by Raomengnan on 2016/9/4.
 */
@Service
public class TimeShaftServiceImpl implements TimeShaftService{

    @Autowired
    private TimeShaftRepository repository;

    private Logger logger = Logger.getLogger(TimeShaftServiceImpl.class);

    @Override
    public boolean addImage(TimeShaft timeShaftNode, MultipartFile multipartFile, String rootPath) {
        try {
            if(timeShaftNode == null) {
                timeShaftNode = new TimeShaft();
                timeShaftNode.setDate(new Date(System.currentTimeMillis()));
            }
            String subpath = ResourceEnum.TIMESHAFT.p() +
                    DateTools.dateFormat(timeShaftNode.getDate(), "yyyyMMdd");
            String path = rootPath + "/" + subpath;
            String originalFileName = multipartFile.getOriginalFilename();
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileId = new ObjectId().toString();
            //写文件
            FileUtils.writeFile(path, fileId + suffix, multipartFile.getBytes());

            timeShaftNode.setId(fileId);
            timeShaftNode.setUrl("/" + rootPath + "/" + fileId + suffix);
            return repository.insert(timeShaftNode);
        }catch (Exception e){
            logger.error("Add Image To Time Shaft: " + e);
        }
        return false;
    }


    @Override
    public boolean deleteNodeById(String id, String rootPath) {
        TimeShaft node = repository.findById(id);
        if (node != null){
            String url = node.getUrl();
            String path = rootPath + "/" + url;
            boolean res = repository.deleteById(id);
            if(res)
                try {
                    FileUtils.deleteFile(path);
                }catch (Exception e){
                    logger.error("deleteNodeById:" + e);
                }

            return res;
        }
        return false;
    }

    @Override
    public boolean updateInfo(TimeShaft shaftNode) {
        if(shaftNode == null)
            return false;

        TimeShaft shaft = repository.findById(shaftNode.getId());
        if(shaft == null)
            return false;
        shaft.setDate(shaftNode.getDate());
        shaft.setIntro(shaftNode.getIntro());
        return repository.save(shaft);
    }

    @Override
    public Page<TimeShaft> findPage(int pno, int pSize) {
        return repository.findPage(pno, pSize);
    }
}
