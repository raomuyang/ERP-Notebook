package org.jufe.erp.repository.photo;

import org.jufe.erp.entity.TimeShaft;
import org.jufe.erp.repository.BaseInterface;
import org.jufe.erp.repository.Page;

/**
 * Created by raomengnan on 16-8-31.
 */
public interface TimeShaftRepository extends BaseInterface<TimeShaft> {

    boolean updateIntro(String id, String intro);

    Page<TimeShaft> findPage(int pno, int pSize);
}
