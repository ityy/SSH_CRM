package cn.yang.service.impl;

import cn.yang.dao.CustomerDao;
import cn.yang.domain.Customer;
import cn.yang.service.CustomerService;
import cn.yang.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    //添加set方法，以供spring注入
    private CustomerDao customerDao;

    @Override
    public PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
        //1 调用Dao查询记录总数
        Integer totalcount = customerDao.getTotalCount(detachedCriteria);
        //2 创建PageBean对象
        PageBean pageBean = new PageBean(currentPage, totalcount, pageSize);
        //3 调用Dao查询分页列表数据
        List<Customer> list = customerDao.getPageList(detachedCriteria, pageBean.getStart(), pageBean.getPageSize());
        //4 放入PageBean 将其返回
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public List<Object[]> getIndustryCount() {
        return customerDao.getIndustryCount();
    }

    @Override
    public void save(Customer customer) {
        //1 维护多表关系
        //由于customer的3个外键值已由Action获得，这里不再设置。
        //2 保存客户
        System.out.println("Service:CustomerServiceImpl:save--正在运行");
        customerDao.save(customer);

    }

    @Override
    public Customer getById(Long cust_id) {
        return customerDao.getById(cust_id);
    }

    @Override
    public void saveOrUpdate(Customer customer) {
        customerDao.saveOrUpdate(customer);
    }

    /*
        --------------------  框架赋值部分  -----------------------------
     */
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}
