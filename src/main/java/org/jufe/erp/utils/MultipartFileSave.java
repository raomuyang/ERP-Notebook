package org.jufe.erp.utils;

import com.qiniu.http.Response;
import org.bson.types.ObjectId;
import org.jufe.erp.utils.enums.ResourceEnum;
import org.jufe.erp.utils.qiniu.QiniuQOS;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Raomengnan on 2016/11/14.
 */
public class MultipartFileSave {
    public static String save2Local(MultipartFile multipartFile, String rootPath, ResourceEnum resource) throws IOException {

        String subpath = resource.p() + "/"
                + DateTools.dateFormat(new Date(System.currentTimeMillis()), "yyyyMMdd");
        String fileId = new ObjectId().toString();
        String path = rootPath + "/" + subpath;

        String originalFileName = multipartFile.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String filename = fileId + suffix;

        boolean res = FileUtils.writeFile(path, filename, multipartFile.getBytes());
        if (res)
            return "/" + subpath + "/" + filename;
        return null;
    }

    public static String save2Qiniu(QiniuQOS qos, MultipartFile multipartFile, ResourceEnum resource, String fileId) throws IOException {
        String subpath = resource.p();
        if(fileId == null)
            fileId = DateTools.dateFormat(new Date(), "yyyyMMdd") + "/"
                    + new ObjectId().toHexString();

        String originalFileName = multipartFile.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = fileId + suffix;

        String key = subpath + "/" + fileName;
        Response response = qos.upload(multipartFile.getBytes(), key, true);
        if(response.isOK())
            return key;
        return null;
    }
}
