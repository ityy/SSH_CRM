package cn.yang.dao.impl;

import cn.yang.dao.UserDao;
import cn.yang.domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User getByUserCode(String usercode) {
//      final   String  temp =usercode;
        return getHibernateTemplate().execute(
                new HibernateCallback<User>() {
                    @Override
                    public User doInHibernate(Session session) throws HibernateException {
//                        String sql = "select * from sys_user where user_code = ?";
//                        SQLQuery sqlQuery = session.createSQLQuery(sql);
//                        sqlQuery.setParameter(0, usercode);
//                        return (User) sqlQuery.uniqueResult();

                        //sql查询的结果不能转为User 很奇怪 改用hql查询
                        String hql = "from User where user_code = ?";
                        Query query = session.createQuery(hql);
                        query.setParameter(0, usercode);
                        return (User) query.uniqueResult();
                    }
                }
        );
    }

}
