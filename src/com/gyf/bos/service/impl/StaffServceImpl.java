package com.gyf.bos.service.impl;

import com.gyf.bos.dao.IStaffDao;
import com.gyf.bos.model.Staff;
import com.gyf.bos.service.IStaffServce;
import com.gyf.bos.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class StaffServceImpl extends BaseServiceImpl<Staff> implements IStaffServce {

    @Autowired
    private IStaffDao staffDao;
    @Override
    public void save(Staff entity) {
        staffDao.save(entity);
    }

    @Override
    public void delete(Staff entity) {
        staffDao.delete(entity);
    }

    @Override
    public void update(Staff entity) {
        staffDao.update(entity);
    }

    @Override
    public Staff find(Serializable id) {
        return staffDao.find(id);
    }

    @Override
    public List<Staff> findAll() {
        return staffDao.findAll();
    }
}
