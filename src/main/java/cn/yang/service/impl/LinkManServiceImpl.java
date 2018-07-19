package cn.yang.service.impl;

import cn.yang.dao.LinkManDao;
import cn.yang.domain.LinkMan;
import cn.yang.service.LinkManService;
import cn.yang.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class LinkManServiceImpl implements LinkManService {
    private LinkManDao linkManDao;
    @Override
    public void saveOrUpdate(LinkMan linkMan) {
        linkManDao.saveOrUpdate(linkMan);
    }

    @Override
    public PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
        //1 调用Dao查询记录总数
        Integer totalcount = linkManDao.getTotalCount(detachedCriteria);
        //2 创建PageBean对象
        PageBean pageBean = new PageBean(currentPage, totalcount, pageSize);
        //3 调用Dao查询分页列表数据
        List<LinkMan> list = linkManDao.getPageList(detachedCriteria, pageBean.getStart(), pageBean.getPageSize());
        //4 放入PageBean 将其返回
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public LinkMan getById(Long lkm_id) {
        return linkManDao.getById(lkm_id);
    }
/*
        --------------------  框架赋值部分  -----------------------------
     */

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }
}
