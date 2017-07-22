package org.jufe.erp.component;

import org.jufe.erp.utils.qiniu.QiniuQOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Raomengnan on 2016/11/14.
 */
@Component
public class QOSComponent {
    @Autowired
    private QiniuConfig qiniuConfig = new QiniuConfig();
    private QiniuQOS qiniuQOS;

    public QiniuQOS getQos(){
        if(qiniuQOS == null)
            qiniuQOS = new QiniuQOS(qiniuConfig.getAccessKey(),
                    qiniuConfig.getSecretKey(),
                    qiniuConfig.getBucket(), qiniuConfig.getZone());
        return qiniuQOS;
    }

    public String getHost(){
        return qiniuConfig.getHost();
    }
}
