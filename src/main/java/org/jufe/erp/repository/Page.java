package org.jufe.erp.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raomengnan on 16-8-31.
 */
public class Page<T>{
    private static int DEFAULT_PAGE_SIZE = 15;
    private long count;
    private int pageSize;
    private long pno = 1; //current page number

    private List<T> data;

    public Page(){
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.data = new ArrayList<T>();
    }
    public Page(int pno, long count){
        this(pno, count, DEFAULT_PAGE_SIZE);
    }

    public Page(int pno, long count, int pageSize){
        this.count = count;
        this.pageSize = pageSize;
        this.data = new ArrayList<T>();

        if(hasPageNo(pno))
            this.pno = pno;
        else
            this.pno = getPageCount() == 0?1:getPageCount();

    }

    public boolean hasNextPage(){
        return ( count - pno * pageSize ) > 0;
    }

    public boolean hasPrePage(){
        return pno > 1;
    }

    public boolean hasPageNo(long pageNo){
        return count - (pageNo - 1) * pageSize > 0;
    }

    public long getPageCount() {
        long pageCount = count / pageSize;
        if(count % pageSize != 0)
            pageCount++;
        return pageCount;
    }

    public long getDataSkip(){
        return (pno - 1) * pageSize;
    }


    public long getDatatart(){
        if(count == 0)
            return 0;
        return ( pno - 1) * pageSize + 1;
    }

    public long getDataEnd(){
        if(count == 0)
            return 0;
        return pageSize + getDatatart() - 1;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


    public long getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}