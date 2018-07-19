package cn.yang.dao.impl;

import cn.yang.dao.BaseDictDao;
import cn.yang.domain.BaseDict;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {
    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {
        //创建离线查询对象
        DetachedCriteria dc =  DetachedCriteria.forClass(BaseDict.class);
        //添加查询条件
        dc.add(Restrictions.eq("dict_type_code", dict_type_code));
        //查询
        List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);

        return list;
    }
}
