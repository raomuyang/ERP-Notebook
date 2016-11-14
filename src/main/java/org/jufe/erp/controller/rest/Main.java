package org.jufe.erp.controller.rest;

import org.jufe.erp.repository.QOSComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Raomengnan on 2016/11/14.
 */
@RestController
@RequestMapping("/rest")
public class Main {
    @Autowired
    private QOSComponent qosComponent;

    @RequestMapping("/obj-store")
    public String objectStore(){
        return qosComponent.getHost();
    }
}
