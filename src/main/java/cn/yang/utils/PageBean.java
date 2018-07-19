package cn.yang.utils;

import java.util.List;

public class PageBean {
    private Integer currentPage;
    private Integer totalCount;
    private Integer pageSize;
    private Integer totalPage;
    //分页列表的数据，为了通用不加泛型
    private List list;

    public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {

        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        if (this.currentPage == null) {
            //未指定页面，则显示第一页
            this.currentPage = 1;
        }

        if (this.pageSize == null) {
            //未指定页面显示的数量，则只显示3条
            this.pageSize = 3;
        }


        //计算总页数 这里用了数学方法的技巧。假如100条，每页10条，则（100+10-1）/10=10，多一条就变11页
        this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;

        //判断currentPage是否越界
        if (this.currentPage < 1) {
            this.currentPage = 1;
        } else if (this.currentPage > this.totalPage) {
            this.currentPage = this.totalPage;
        }


    }

    //计算SQL起始索引
    public int getStart() {
        return (this.currentPage - 1) * this.pageSize;

    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
