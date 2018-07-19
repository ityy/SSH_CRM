package cn.yang.service;

import cn.yang.domain.LinkMan;
import cn.yang.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkManService {
    void saveOrUpdate(LinkMan linkMan);

    PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

    LinkMan getById(Long lkm_id);
}
