package org.jufe.erp.repository.about;

import org.jufe.erp.entity.ERPInfo;
import org.jufe.erp.repository.BaseInterface;

/**
 * Created by Raomengnan on 2016/8/31.
 */
public interface ERPInfoRepository extends BaseInterface<ERPInfo> {
    static final String id = "org.jufe.erp";

    ERPInfo getInfo();

    boolean update(ERPInfo info);

    boolean updateTel(String tel);

    boolean updateIntro(String intro);

    boolean updateJoinUs(String context);

    boolean updateOrg(String context);
}
