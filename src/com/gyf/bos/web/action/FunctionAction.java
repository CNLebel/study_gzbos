package com.gyf.bos.web.action;

import com.gyf.bos.model.Function;
import com.gyf.bos.model.Staff;
import com.gyf.bos.web.action.base.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FunctionAction extends BaseAction<Function> {


    @Override
    public String save() {
        return null;
    }

    @Override
    public String delete() throws IOException {
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


    public void pageQuery() throws IOException {
        //接收参数 page[当前页], rows[每页显示多少条]
        //调用servce 参数里传一个pageBean
        //封装条件
        pb.setCurrentPage(page);
        pb.setPageSize(rows);

        //调用service, 参数里传一个PabeBean
        functionService.pageQuery(pb);

        //返回json数据
        responseJson(pb, new String[]{"currentPage","pageSize","detachedCriteria", "function", "functions"});
    }

//    public void listJson() throws IOException {
//
//        List<Staff> list = functionService.findAllWithNoDelete();
//
//        responseJson(list, new String[]{"telephone", "haspda", "deltag", "station", "standard"});
//
//    }

}
