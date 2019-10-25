package com.gyf.bos.web.action;

import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Staff;
import com.gyf.bos.service.IStaffService;
import com.gyf.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StaffAction extends BaseAction<Staff> {

    Logger logger = Logger.getLogger(StaffAction.class);

    @Override
    public String save() {

        logger.info(getModel());

        staffServce.save(getModel());
        return SUCCESS;
    }



    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String delete() throws IOException {


        staffServce.deleteBatch(ids);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.getWriter().write("success");

        return NONE;
    }

    @Override
    public String update() {

        //getModel() [游离/脱管状态 session没有缓存，有id]

        //根据id从数据库获取数据[持久状态-session有缓存，有id]
        Staff staff = staffServce.find(getModel().getId());

        //更新数据库的数据
        staff.setName(getModel().getName());
        staff.setName(getModel().getTelephone());
        staff.setName(getModel().getStation());
        staff.setName(getModel().getHaspda());
        staff.setName(getModel().getStandard());
        staffServce.update(staff);

        return NONE;
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
        //封装条件
        pb.setCurrentPage(page);
        pb.setPageSize(rows);

        //调用service, 参数里传一个PabeBean
        staffServce.pageQuery(pb);

        //返回json数据
        responseJson(pb, new String[]{"currentPage","pageSize","detachedCriteria"});
    }

    public void listJson() throws IOException {

        List<Staff> list = staffServce.findAllWithNoDelete();

        responseJson(list, new String[]{"telephone", "haspda", "deltag", "station", "standard"});

    }

}
