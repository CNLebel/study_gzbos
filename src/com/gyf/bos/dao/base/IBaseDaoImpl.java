package com.gyf.bos.dao.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;
import java.util.List;

public class IBaseDaoImpl<T> implements IBaseDao<T>{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(T entity) {
        this.hibernateTemplate.save(entity);
    }

    @Override
    public void delete(T entity) {
        this.hibernateTemplate.delete(entity);
    }

    @Override
    public void update(T entity) {
        this.hibernateTemplate.update(entity);
    }

    @Override
    public T find(Serializable id) {
        return null;
    }

    @Override
    public List<T> find() {
        return null;
    }
}
