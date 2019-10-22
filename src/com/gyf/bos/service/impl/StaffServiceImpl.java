package com.gyf.bos.service.impl;

import com.gyf.bos.dao.IStaffDao;
import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Staff;
import com.gyf.bos.service.IStaffService;
import com.gyf.bos.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Service
@Transactional   //事务是由事务管理实现
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements IStaffService {

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

    @Override
    public void pageQuery(PageBean<Staff> pb) {
        staffDao.pageQuery(pb);
    }

    @Override
    public void deleteBatch(String ids) {

        String hql = "UPDATE Staff SET deltag = ? WHERE id = ?";

        //拆分id
        String[] idsArr = ids.split(",");
        for(String id:idsArr){
            staffDao.executeUpdate(hql, "1" , id);
//            staffDao.executeUpdateByQueryName("staff.delete","1",id);
        }


    }
}
