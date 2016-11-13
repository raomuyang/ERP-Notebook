package org.jufe.erp.utils.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import com.qiniu.http.Response;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by Raomengnan on 2016/11/13.
 * 简单封装一些七牛云存储的操作
 * token 默认 3600秒失效
 */
public class QiniuQOS {
    private Auth auth;
    private Zone zone;
    private String bucket;
    private Configuration configuration;

    private Logger logger = Logger.getLogger(QiniuQOS.class);

    public QiniuQOS(String accessKey, String secretKey, String bucket, Zone z){
        auth = Auth.create(accessKey, secretKey);
        zone =z;
        this.bucket = bucket;
        configuration = new Configuration(zone);
    }

    public UploadManager getUploadManager(){
        return new UploadManager(configuration);
    }

    public BucketManager getBucketManager(){
        return new BucketManager(auth, configuration);
    }

    public Response upload(String filePath, String fileKey, boolean override) throws IOException{
        Response response = null;
        UploadManager uploadManager = getUploadManager();
        try {
            String token = override?getModifyUploadToken(fileKey) : getUploadToken();
            response = uploadManager.put(filePath, fileKey, token);
        }catch (QiniuException e){
            response = e.response;
            logger.error(response.toString());
        }
        return response;
    }

    public Response upload(byte[] data, String fileKey, boolean override) throws IOException{
        Response response = null;
        UploadManager uploadManager = getUploadManager();
        try {
            String token = override?getModifyUploadToken(fileKey) : getUploadToken();
            response = uploadManager.put(data, fileKey, token);
        }catch (QiniuException e){
            response = e.response;
            logger.error(response.toString());
        }
        return response;
    }

    public Response upload(byte[] data, String fileKey, String token){
        Response response = null;
        UploadManager uploadManager = getUploadManager();
        try {
            if(token == null)
                token = getUploadToken();
            response = uploadManager.put(data, fileKey, token);
        }catch (QiniuException e){
            response = e.response;
            logger.error(e.response.toString());
        }
        return response;
    }

    public String getUploadToken(){
        return auth.uploadToken(bucket);
    }

    public String getModifyUploadToken(String fileKey){
        return auth.uploadToken(bucket, fileKey);
    }

    public FileInfo fileInfo(String key){
        try {
            return getBucketManager().stat(bucket, key);
        } catch (QiniuException e) {
            Response r = e.response;
            logger.error(r.toString());
        }
        return null;
    }

    /**
     * 以 "/"作为目录的分隔符号，模拟目录结构
     * @param prefix 如“blog-img/”
     * @return FileListing
     * FileListing.commonPrefixes是该目录下的下一层目录的前缀(可能为空):
     *        blog-img/20160930/
     *        blog-img/20161113/
     * FileListing.items
     *        该目录下的文件
     * FileListing.isEOF()
     *        是否是列表的结尾
     * FileListing.marker
     *        用于下一次迭代
     */
    public FileListing listFile(String prefix, String marker){
        BucketManager bucketManager = getBucketManager();
        try {
            FileListing fileListing = bucketManager.listFiles(bucket, prefix, marker, 100, "/");
            return fileListing;
        } catch (QiniuException e) {
            Response response = e.response;
            System.out.println(response.toString());
        }
        return null;
    }


    public boolean deleteFile(String fileKey){
        BucketManager bucketManager = getBucketManager();
        try {
            bucketManager.delete(bucket, fileKey);
            return true;
        } catch (QiniuException e) {
            System.out.println(e);
            logger.error(e.response.toString());
            return false;
        }
    }
    public static enum Zones{
        EAST(Zone.zone0()),
        NORTH(Zone.zone1()),
        SOUTH(Zone.zone2()),
        AUTO(Zone.autoZone());

        Zone zone;
        Zones(Zone z){
            zone = z;
        }

        Zone v(){
            return this.zone;
        }
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone z) {
        this.zone = z;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}

