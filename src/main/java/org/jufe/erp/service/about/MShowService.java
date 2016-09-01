package org.jufe.erp.service.about;

import org.jufe.erp.entity.MShow;

import java.util.List;

/**
 * Created by Raomengnan on 2016/9/1.
 */
public interface MShowService {

    public MShow getMShow();

    public boolean update(MShow mShow);

    public boolean updateIurls(List<String> iurls);

    public boolean updateVurl(String vurl);
    public boolean updateWords(String words);
    /**
     * 更新历史首页图片链接
     * @param iHistory
     * @return
     */
    public boolean updateIHistory(List<String> iHistory);

    /**
     * 更新历史视频链接
     * @param vHistory
     * @return
     */
    public boolean updateVHistory(List<String> vHistory);

}
