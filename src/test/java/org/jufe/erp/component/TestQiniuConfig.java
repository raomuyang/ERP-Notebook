package org.jufe.erp.component;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by rao-mengnan on 2017/7/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("org.jufe.erp.component")
@SpringBootTest(classes = TestQiniuConfig.class)
public class TestQiniuConfig {

    @Autowired
    QiniuConfig qiniuConfig;

    @Test
    public void readConfig() {
        Assert.assertNotNull(qiniuConfig);
        System.out.println(qiniuConfig.getAccessKey());
    }
}
