package cn.yang.dao;

import cn.yang.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao extends BaseDao<Customer> {
    Integer getTotalCount(DetachedCriteria detachedCriteria);

    List<Customer> getPageList(DetachedCriteria detachedCriteria, int start, Integer pageSize);

    List<Object[]> getIndustryCount();
}
