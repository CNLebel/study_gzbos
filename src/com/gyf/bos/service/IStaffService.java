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
}
