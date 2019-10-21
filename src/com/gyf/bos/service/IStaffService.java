package com.gyf.bos.service;

import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Staff;
import com.gyf.bos.service.base.IBaseService;

public interface IStaffService extends IBaseService<Staff> {

    /**
     * 分布查询
     * @param pb
     * */
    public void pageQuery(PageBean<Staff> pb);


    /**
     * 批量删除取派员
     * @param  ids  [001, 002, 003]     以逗号隔开
     * */
    public void deleteBatch(String ids);
}
