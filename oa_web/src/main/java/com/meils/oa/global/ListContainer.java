package com.meils.oa.global;


import java.util.List;

/**
 * List 的包装类
 */
public class ListContainer<T> {

    private List<T> list;
    private Integer page;
    private Integer pageSize;
    private Integer totals;
    private Integer totalpages;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }

    public Integer getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(Integer totalpages) {
        this.totalpages = totalpages;
    }


    public ListContainer(List<T> p) {
        this.list = p;
    }

    public List<T> getData() {
        return list;
    }

    public void setData(List<T> list) {
        this.list = list;
    }

}
