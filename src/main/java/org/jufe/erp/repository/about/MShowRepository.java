package org.jufe.erp.repository.about;

import org.jufe.erp.entity.MShow;
import org.jufe.erp.repository.BaseRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Raomengnan on 2016/8/30.
 */
@Repository
public class MShowRepository extends BaseRepository<MShow> {
    private static final String id = "erp.mainshow";

    public MShow getMShow(){
        return super.findById(id);
    }

    public boolean update(MShow mShow){
        if(!mShow.getId().equals(id))
            return false;
        return super.save(mShow);
    }

    public boolean updateIurls(List<String> iurls){
        MShow mShow = getMShow();

        List<String> pushList = new ArrayList<>();
        for (String url: iurls)
            if(mShow.getiHistory().contains(url))
                pushList.add(url);
        super.update(new Query(new Criteria("id").is(id)),
                new Update().push("iHistory", pushList));

        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("iurls", iurls));
    }

    public boolean updateVurl(String vurl){
        super.update(new Query(new Criteria("id").is(id)),
                new Update().push("vHistory", vurl));
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("vurl", vurl));
    }

    public boolean updateWords(String words){
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("words", words));
    }

    /**
     * 更新历史首页图片链接
     * @param iHistory
     * @return
     */
    public boolean updateIHistory(List<String> iHistory){
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("iHistory", iHistory));
    }

    /**
     * 更新历史视频链接
     * @param vHistory
     * @return
     */
    public boolean updateVHistory(List<String> vHistory){
        return super.update(new Query(new Criteria("id").is(id)),
                new Update().set("iHistory", vHistory));
    }

}
