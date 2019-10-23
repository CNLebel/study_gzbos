package com.gyf.bos.web.action;

import com.gyf.bos.model.Region;
import com.gyf.bos.model.Subarea;
import com.gyf.bos.service.IRegionService;
import com.gyf.bos.service.ISubareaService;
import com.gyf.bos.utils.PinYin4jUtils;
import com.gyf.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaService;

    @Override
    public String save() {

        // 调用service
        subareaService.save(getModel());

        return SUCCESS;
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
        pb.setCurrentPage(page);
        pb.setPageSize(rows);

        //调用service, 参数里传一个PabeBean
        subareaService.pageQuery(pb);

        /**
         * 注意：获取数据时，把分区的subarea的Region的加载方式设置为即时加载
         * */

        //返回json数据
        responseJson(pb, new String[]{"currentPage","pageSize","detachedCriteria","subareas"});
    }
}
