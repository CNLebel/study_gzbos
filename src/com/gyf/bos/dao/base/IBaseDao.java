package com.gyf.bos.dao.base;

import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    public void save(T entity);
    public void delete(T entity);
    public void update(T entity);
    public T find(Serializable id);

    public List<T> findAll();

    public void executeUpdate(String hql,Object... objs);

    public void executeUpdateByQueryName(String queryName,Object... objs);
}
