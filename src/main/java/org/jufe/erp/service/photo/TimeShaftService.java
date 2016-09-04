package org.jufe.erp.service.photo;

import org.jufe.erp.entity.TimeShaft;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface TimeShaftService{

    public boolean addImage(TimeShaft timeShaft, MultipartFile multipartFile, String realPath);

    public boolean deleteNodeById(String id, String realPath);

    public boolean updateIntro(String id, String intro);

    public Page<TimeShaft> findPage(int pno, int pSize);

}
