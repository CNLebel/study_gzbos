package com.gyf.bos.web.action;

import com.gyf.bos.model.Staff;
import com.gyf.bos.model.User;
import com.gyf.bos.service.IStaffServce;
import com.gyf.bos.service.IUserService;
import com.gyf.bos.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StaffAction extends BaseAction<Staff> {

    @Autowired
    private IStaffServce staffServce;


    @Override
    public String save() {
        System.out.println(getModel());
        staffServce.save(getModel());
        return SUCCESS;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String update() {
        return null;
    }

    @Override
    public String list() {
        return null;
    }
}
