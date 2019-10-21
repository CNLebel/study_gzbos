package com.gyf.bos.web.action;

import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Staff;
import com.gyf.bos.service.IStaffService;
import com.gyf.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StaffAction extends BaseAction<Staff> {

    @Autowired
    private IStaffService staffServce;


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

    private int page;
    private int rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void pageQuery() throws IOException {
        //接收参数 page[当前页], rows[每页显示多少条]

        //调用servce 参数里传一个pageBean

        PageBean<Staff> pb = new PageBean<Staff>();

        //封装条件
        pb.setCurrentPage(page);
        pb.setPageSize(rows);
        //条件：from Staff
        DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
        pb.setDetachedCriteria(dc)  ;

        staffServce.pageQuery(pb);

        //返回json
         //排除不需要转json的属性
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});

         //创建json对象
        JSONObject jsonObject = JSONObject.fromObject(pb, config);

         //响应
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("content-Type","text/json;charset=utf-8");
        response.getWriter().write(jsonObject.toString());
    }
}
