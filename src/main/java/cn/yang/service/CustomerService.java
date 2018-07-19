package cn.yang.service;

import cn.yang.domain.Customer;
import cn.yang.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {
    public PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

    public void save(Customer customer);

    public Customer getById(Long cust_id);

    public void saveOrUpdate(Customer customer);

    List<Object[]> getIndustryCount();
}
