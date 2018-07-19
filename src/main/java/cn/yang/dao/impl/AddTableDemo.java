package cn.yang.dao.impl;

import cn.yang.domain.BaseDict;
import cn.yang.domain.Customer;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.test.context.TestExecutionListeners;

import java.math.BigInteger;

//独立单元测试 使用hibernate根据orm映射配置自动创建表并填入数据
public class AddTableDemo {

    @Test
    public void addCustomer() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session ss = sf.openSession();

        Transaction tx = ss.beginTransaction();
        //------------------------

        Customer ct1 = new Customer();
        BaseDict bd1 = new BaseDict();
        BaseDict bd2 = new BaseDict();
        BaseDict bd3 = new BaseDict();

        bd1.setDict_id("1");
        bd2.setDict_id("2");
        bd3.setDict_id("3");

        ct1.setCust_industry(bd1);
        ct1.setCust_level(bd2);
        ct1.setCust_source(bd3);

        ct1.setCust_name("测试公司名1");


        ss.save(ct1);


        //------------------------
        tx.commit();
        ss.close();
        sf.close();



    }

    @Test
    public void getTT() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session ss = sf.openSession();

        Transaction tx = ss.beginTransaction();
        //------------------------
        String sql = "select count(*) from cst_customer";
        SQLQuery sqlQuery = ss.createSQLQuery(sql);
        BigInteger i = (BigInteger)sqlQuery.uniqueResult();
        System.out.println( i);

        //------------------------
        tx.commit();
        ss.close();
        sf.close();
    }

}
