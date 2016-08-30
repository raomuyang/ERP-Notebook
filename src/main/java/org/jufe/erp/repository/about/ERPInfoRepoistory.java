package org.jufe.erp.repository.about;

import org.jufe.erp.entity.ERPInfo;
import org.jufe.erp.repository.BaseRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Raomengnan on 2016/8/30.
 */
@Repository
public class ERPInfoRepoistory extends BaseRepository<ERPInfo> {
    private static final String id = "org.jufe.erp";

    public ERPInfo getInfo(){
        return super.findById(id);
    }

    public boolean update(ERPInfo info){
        if(!info.getId().equals(id))
            return false;
        return super.save(info);
    }

    public boolean updateTel(String tel){
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("tel", tel));
    }

    public boolean updateIntro(String intro){
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("intro", intro));
    }

    public boolean updateJoinUs(String context){
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("joinUs", context));
    }

    public boolean updateOrg(String context){
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("org", context));
    }
}
