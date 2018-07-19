package cn.yang.dao.impl;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import cn.yang.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    //用于标识运行时 给定泛型的类型
    private Class clazz;

    //在构造方法中获取泛型类型
    public BaseDaoImpl() {
        //获得带有泛型类型的父类 因为此基类就是其他实现类的父类 等于获得的是自己
        ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得泛型类型 因泛型允许多个，这里返回的是数组，注意用下标接收
        clazz = (Class) ptClass.getActualTypeArguments()[0];
    }

    @Override
    // save方法没配置事务会出错
    public void save(T t) {
//        SessionFactory sf = new Configuration().configure().buildSessionFactory();
//        Session ss = sf.openSession();
//
//        Transaction tx = ss.beginTransaction();
//
//
//        //------------------------
//        ss.save(t);
//
//        //------------------------
//        tx.commit();
//        ss.close();
//        sf.close();

        getHibernateTemplate().save(t);


    }

    @Override
    public void saveOrUpdate(T t) {
        getHibernateTemplate().saveOrUpdate(t);

    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    @Override
    public void deleteById(Serializable id) {
        //先取再删
        T t = this.getById(id);
        this.delete(t);
    }

    @Override
    public void update(T t) {
        getHibernateTemplate().update(t);

    }

    @Override
    public T getById(Serializable id) {
        //对象类型已经在构造方法中获得，这里直接可以用
        return (T) getHibernateTemplate().get(clazz, id);
    }

    @Override
    public Integer getTotalCount(DetachedCriteria detachedCriteria) {
        //设置聚合函数，查询后再将设置清空
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(detachedCriteria);
        detachedCriteria.setProjection(null);

        //取出数值
        if (list != null && list.size() > 0) {
            //出去第一个值并转为Integer返回
            return list.get(0).intValue();
        } else {
            return null;
        }

    }

    @Override
    public List<T> getPageList(DetachedCriteria dc, int start, Integer pageSize) {
        List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
        return list;
    }


}
