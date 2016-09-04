package org.jufe.erp.service.about.impl;

import org.jufe.erp.entity.ERPInfo;
import org.jufe.erp.repository.about.ERPInfoRepository;
import org.jufe.erp.service.about.ERPInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Raomengnan on 2016/9/1.
 */
@Service
public class ERPInfoServiceImpl implements ERPInfoService{
    @Autowired
    private ERPInfoRepository erpInfoRepository;


    @Override
    public ERPInfo getInfo() {
        return erpInfoRepository.getInfo();
    }

    @Override
    public String getTel() {
        return getInfo().getTel();
    }

    @Override
    public String getIntro() {
        return getInfo().getIntro();
    }

    @Override
    public String getJoinUs() {
        return getInfo().getJoinUs();
    }

    @Override
    public String getOrgStruct() {
        return getInfo().getOrg();
    }

    @Override
    public boolean update(ERPInfo info) {
        return erpInfoRepository.update(info);
    }

    @Override
    public boolean updateTel(String tel) {
        return erpInfoRepository.updateTel(tel);
    }

    @Override
    public boolean updateIntro(String intro) {
        return erpInfoRepository.updateIntro(intro);
    }

    @Override
    public boolean updateJoinUs(String context) {
        return erpInfoRepository.updateJoinUs(context);
    }

    @Override
    public boolean updateOrg(String context) {
        return erpInfoRepository.updateOrg(context);
    }
}
