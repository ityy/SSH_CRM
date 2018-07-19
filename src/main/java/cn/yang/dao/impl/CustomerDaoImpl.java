package cn.yang.dao.impl;

import cn.yang.dao.CustomerDao;
import cn.yang.domain.Customer;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.List;

//继承spring提供的Hibernate模板
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {


    //独立单元测试 测试数据库读取是否正常 开发时使用
    @Test
    public void getTotalCountTest() {

        SessionFactory sf = new Configuration().configure().buildSessionFactory();

        setSessionFactory(sf);

        Object i = getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "select count(*) from cst_customer";
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                Object i = (Object) sqlQuery.uniqueResult();
                return i;
            }
        });
        System.out.println(i);
    }

    @Override
    public List<Object[]> getIndustryCount() {
        List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {
            String sql = " SELECT                                 " +
                    "  bd.`dict_item_name`,                   " +
                    "  COUNT(*) total                         " +
                    " FROM                                    " +
                    "  cst_customer c,                        " +
                    "  base_dict bd                           " +
                    " WHERE c.`cust_industry` = bd.`dict_id`  " +
                    " GROUP BY c.`cust_industry`              ";

            @Override
            public List doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                return query.list();
            }
        });
        return list;

    }
}
