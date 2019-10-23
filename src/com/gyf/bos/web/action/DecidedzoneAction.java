package com.gyf.bos.web.action;

import com.gyf.bos.model.Decidedzone;
import com.gyf.bos.model.Region;
import com.gyf.bos.service.IDecidedzoneService;
import com.gyf.bos.service.IRegionService;
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

public class DecidedzoneAction extends BaseAction<Decidedzone> {

    private String[] subareaId;

    public String[] getSubareaId() {
        return subareaId;
    }

    public void setSubareaId(String[] subareaId) {
        this.subareaId = subareaId;
    }

    @Autowired
    private IDecidedzoneService decidedzoneService;

    @Override
    public String save() {

        // 调用service
        decidedzoneService.save(getModel(), subareaId);

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
        pb.setCurrentPage(page);
        pb.setPageSize(rows);

        //调用service, 参数里传一个PabeBean
        decidedzoneService.pageQuery(pb);


        //返回json数据
        responseJson(pb, new String[]{"currentPage","pageSize","detachedCriteria"});
    }
}
