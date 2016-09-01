package org.jufe.erp.service.about;

import org.jufe.erp.entity.ERPInfo;

/**
 * Created by Raomengnan on 2016/9/1.
 */
public interface ERPInfoService {

    public ERPInfo getInfo();

    public String getTel();

    public String getIntro();

    public String getJoinUs();

    public String getOrgStruct();

    public boolean update(ERPInfo info);

    public boolean updateTel(String tel);

    public boolean updateIntro(String intro);

    public boolean updateJoinUs(String context);

    public boolean updateOrg(String context);
}
