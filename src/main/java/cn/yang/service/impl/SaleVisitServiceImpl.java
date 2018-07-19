package cn.yang.service.impl;

import cn.yang.dao.SaleVisitDao;
import cn.yang.domain.SaleVisit;
import cn.yang.service.SaleVisitService;
import cn.yang.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class SaleVisitServiceImpl implements SaleVisitService {
    private SaleVisitDao saleVisitDao;

    @Override
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);

    }

    @Override
    public PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
        //1 调用Dao查询记录总数
        Integer totalcount = saleVisitDao.getTotalCount(detachedCriteria);
        //2 创建PageBean对象
        PageBean pageBean = new PageBean(currentPage, totalcount, pageSize);
        //3 调用Dao查询分页列表数据
        List<SaleVisit> list = saleVisitDao.getPageList(detachedCriteria, pageBean.getStart(), pageBean.getPageSize());
        //4 放入PageBean 将其返回
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public SaleVisit getById(String visit_id) {

        return saleVisitDao.getById(visit_id);
    }

    public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
        this.saleVisitDao = saleVisitDao;
    }
}
