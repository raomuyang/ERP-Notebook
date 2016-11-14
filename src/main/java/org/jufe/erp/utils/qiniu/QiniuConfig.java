package org.jufe.erp.utils.qiniu;

import com.qiniu.common.Zone;

/**
 * Created by Raomengnan on 2016/11/14.
 */
public class QiniuConfig {
    String accessKey;
    String secretKey;
    int zone;
    String bucket;
    String host;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Zone getZone() {
        switch (zone){
            case 0:
                return Zone.zone0();
            case 1:
                return Zone.zone1();
            case 2:
                return Zone.zone2();
            default:
                return Zone.autoZone();
        }
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
