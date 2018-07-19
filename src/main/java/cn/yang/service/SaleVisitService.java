package cn.yang.service;

import cn.yang.domain.SaleVisit;
import cn.yang.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface SaleVisitService {
    void save(SaleVisit saleVisit);

    PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

    SaleVisit getById(String visit_id);
}
