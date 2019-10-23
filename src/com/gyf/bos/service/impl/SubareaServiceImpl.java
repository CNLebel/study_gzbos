package com.gyf.bos.service.impl;

import com.gyf.bos.dao.IRegionDao;
import com.gyf.bos.dao.ISubareaDao;
import com.gyf.bos.dao.base.IBaseDao;
import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Region;
import com.gyf.bos.model.Subarea;
import com.gyf.bos.service.IRegionService;
import com.gyf.bos.service.ISubareaService;
import com.gyf.bos.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Service
@Transactional   //事务是由事务管理实现
public class SubareaServiceImpl extends BaseServiceImpl<Subarea> implements ISubareaService {

    @Autowired
    private ISubareaDao subareaDao;

    @Override
    public void save(Subarea entity) {
        subareaDao.save(entity);
    }

    @Override
    public void delete(Subarea entity) {

    }

    @Override
    public void update(Subarea entity) {

    }

    @Override
    public Subarea find(Serializable id) {
        return null;
    }

    @Override
    public List<Subarea> findAll() {
        return null;
    }

    @Override
    public void pageQuery(PageBean<Subarea> pb) {
        subareaDao.pageQuery(pb);
    }
}
