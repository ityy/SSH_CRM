package cn.yang.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/*
    抽取共同操作创建baseDAO
    当操作对象不确定时，使用泛型
 */
public interface BaseDao<T> {
    //增
    void save(T t);

    //增或修改 （自动判断是瞬时态还是游离态）
    void saveOrUpdate(T t);

    //删
    void delete(T t);

    //删
    //Serializable基本覆盖了所有ID 是8大数据对象的共同接口
    void deleteById(Serializable id);

    //改
    void update(T t);

    //查 根据ID查对象
    T getById(Serializable id);

    //查 符合条件的记录总数
    Integer getTotalCount(DetachedCriteria detachedCriteria);


    //查 查询分页列表数据
    List<T> getPageList(DetachedCriteria dc, int start, Integer pageSize);

}
