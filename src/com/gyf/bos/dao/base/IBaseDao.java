package com.gyf.bos.dao.base;

import com.gyf.bos.model.PageBean;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    public void save(T entity);
    public void delete(T entity);
    public void update(T entity);
    public T find(Serializable id);

    // 查询所有的数据
    public List<T> findAll();

    // 查询所有的数据
    public List<T> findAllByDetachedCriteria(DetachedCriteria dc);

    public void executeUpdate(String hql,Object... objs);

    public void executeUpdateByQueryName(String queryName,Object... objs);

    public void saveAll(List<T> list);

    public void pageQuery(PageBean<T> pb);
}
