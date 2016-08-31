package org.jufe.erp.repository.about;

import org.jufe.erp.entity.ERPInfo;
import org.jufe.erp.repository.BaseInterface;

/**
 * Created by Raomengnan on 2016/8/31.
 */
public interface ERPInfoRepository extends BaseInterface<ERPInfo>{
    public ERPInfo getInfo();

    public boolean update(ERPInfo info);

    public boolean updateTel(String tel);

    public boolean updateIntro(String intro);

    public boolean updateJoinUs(String context);

    public boolean updateOrg(String context);
}
