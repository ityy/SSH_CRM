package cn.yang.dao.impl;

import cn.yang.dao.UserDao;
import cn.yang.domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User getByUserCode(String usercode) {
        final String temp = usercode;
        return getHibernateTemplate().execute(
                new HibernateCallback<User>() {

                    //                    String sql = "select * from sys_user where user_code = ?";
//                    sql查询的结果不能转为User 很奇怪 改用hql查询
                    String hql = "from User where user_code = ?";

                    @Override
                    public User doInHibernate(Session session) throws HibernateException {

//                        SQLQuery sqlQuery = session.createSQLQuery(sql);
//                        sqlQuery.setParameter(0, usercode);
//                        return (User) sqlQuery.uniqueResult();

                        Query query = session.createQuery(hql);
                        query.setParameter(0, usercode);
                        return (User) query.uniqueResult();
                    }
                }
        );
    }

}
