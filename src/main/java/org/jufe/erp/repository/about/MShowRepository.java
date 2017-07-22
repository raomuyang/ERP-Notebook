package org.jufe.erp.repository.about;

import org.jufe.erp.entity.MShow;
import org.jufe.erp.repository.BaseInterface;

import java.util.List;

/**
 * Created by Raomengnan on 2016/8/30.
 */
public interface MShowRepository extends BaseInterface<MShow> {
    static final String id = "erp.mainshow";

    MShow getMShow();

    boolean update(MShow mShow);

    boolean updateIurls(List<String> iurls);

    boolean updateVurl(String vurl);

    boolean updateWords(String words);

    /**
     * 更新历史首页图片链接
     *
     * @param iHistory
     * @return
     */
    boolean updateIHistory(List<String> iHistory);

    /**
     * 更新历史视频链接
     *
     * @param vHistory
     * @return
     */
    boolean updateVHistory(List<String> vHistory);

}
