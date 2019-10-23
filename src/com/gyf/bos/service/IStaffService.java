package com.gyf.bos.service;

import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Region;
import com.gyf.bos.model.Staff;
import com.gyf.bos.service.base.IBaseService;

import java.util.List;

public interface IStaffService extends IBaseService<Staff> {


    /**
     * 批量删除取派员
     * @param  ids  [001, 002, 003]     以逗号隔开
     * */
    public void deleteBatch(String ids);


    /**
     * 分页查询
     * @param pb
     * */
    public void pageQuery(PageBean<Staff> pb);


    public List<Staff> findAllWithNoDelete();

}
