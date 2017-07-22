package org.jufe.erp.component;

import com.qiniu.common.Zone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Raomengnan on 2016/11/14.
 */
@Component
public class QiniuConfig {
    @Value("${qiniu.accessKey}")
    String accessKey;
    @Value("${qiniu.secretKey}")
    String secretKey;
    @Value("${qiniu.zone}")
    String zone;
    @Value("${qiniu.bucket}")
    String bucket;
    @Value("${qiniu.host}")
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
            case "0":
                return Zone.zone0();
            case "1":
                return Zone.zone1();
            case "2":
                return Zone.zone2();
            default:
                return Zone.autoZone();
        }
    }

    public void setZone(String zone) {
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
