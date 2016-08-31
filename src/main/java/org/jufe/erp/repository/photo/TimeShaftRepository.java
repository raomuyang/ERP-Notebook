package org.jufe.erp.repository.photo;

import org.jufe.erp.entity.TimeShaft;
import org.jufe.erp.repository.BaseRepository;
import org.jufe.erp.repository.Page;
import org.jufe.erp.utils.MongoUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by raomengnan on 16-8-31.
 */
@Repository
public class TimeShaftRepository extends BaseRepository<TimeShaft>{

    public boolean updateIntro(String id, String intro){
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("intro", intro));
    }

    public Page<TimeShaft> findPage(int pno, int pSize){
        return super.findPage(MongoUtil.soryBy(new Query(), MongoUtil.DESC, "date"), pno, pSize);
    }
}
