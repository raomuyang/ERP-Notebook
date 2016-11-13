package org.jufe.erp.utils.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;

/**
 * Created by Raomengnan on 2016/11/13.
 */
public class QiniuDemos {
    String accessKey = "***";
    String secretKey = "***";
    String bucket = "qos-erp";
    QiniuQOS oss = new QiniuQOS(accessKey, secretKey, bucket, QiniuQOS.Zones.EAST.v());

    public static void main(String[] args) {
        QiniuDemos t = new QiniuDemos();
        t.upload(200);

        t.listFile();
        t.listFile();

        t.delete(0,199);
    }

    public void fileInfo(){
        FileInfo fileInfo = oss.fileInfo("erp/test/command.txt");
        System.out.println(fileInfo.hash);
    }

    static String marker = null;
    public void listFile(){
        System.out.println("[START]------------------------");
        FileListing fileListing = oss.listFile("test/", marker);
        FileInfo[] fileInfos =  fileListing.items;
        System.out.println(fileListing.items.length);
        for (FileInfo fileInfo : fileInfos) {
            System.out.println(fileInfo.key);
        }

        System.out.println(fileListing.isEOF()+"\n---------------------------");
        if(fileListing.commonPrefixes != null)
            for (String commonPrefix : fileListing.commonPrefixes) {
                System.out.println(commonPrefix);
            }
        System.out.println("---------------------------\n" + fileListing.marker);
        marker = fileListing.marker;
    }


    public void upload(int num) {
        try {
            String filePath = "D://command.txt";
            String fileKey = "test/command.txt";
            for(int i = 0; i < num; i ++){
                Response response = oss.upload(filePath, fileKey + i, false);
                System.out.println(response.bodyString());
            }

        }catch (QiniuException e1){
            try {
                System.out.println(e1.response.bodyString());
            } catch (QiniuException e) {
                e.printStackTrace();
            }
        }catch (Exception e2){
            System.out.println(e2);
        }

    }

    public void delete(int f, int t){
        String fileKey = "test/command.txt";
        if(f > t){
            f = f + t;
            t = f - t;
            f = f - t;
        }
        for(int i = f; i <= t; i ++){
            System.out.println(oss.deleteFile(fileKey + i));
        }
    }
}

