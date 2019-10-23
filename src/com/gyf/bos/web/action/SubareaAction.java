package com.gyf.bos.web.action;

import com.gyf.bos.model.Region;
import com.gyf.bos.model.Subarea;
import com.gyf.bos.service.IRegionService;
import com.gyf.bos.service.ISubareaService;
import com.gyf.bos.utils.PinYin4jUtils;
import com.gyf.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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

        //添加查询条件
        DetachedCriteria dc = pb.getDetachedCriteria();

        //addressKey:d
        String addressKey = getModel().getAddresskey();
        if(!StringUtils.isEmpty(addressKey)){
            dc.add(Restrictions.like("addressKey","%"+ addressKey +"%"));
        }

        Region region = getModel().getRegion();
        if(region != null){
            //province
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();

            dc.createAlias("region","r");    //创建一个别名

            if(!StringUtils.isEmpty(addressKey)){
                dc.add(Restrictions.like("r.province", "%" + province + "%"));
            }

            //city
            if(!StringUtils.isEmpty(city)){
                dc.add(Restrictions.like("r.city", "%" + city + "%"));
            }

            //district
            if(!StringUtils.isEmpty(district)){
                dc.add(Restrictions.like("r.district", "%" + district + "%"));
            }
        }


        //调用service, 参数里传一个PabeBean
        subareaService.pageQuery(pb);

        /**
         * 注意：获取数据时，把分区的subarea的Region的加载方式设置为即时加载
         * */

        //返回json数据
        responseJson(pb, new String[]{"currentPage","pageSize","detachedCriteria","subareas"});
    }

    // 导出表格[下载一个xls文件]
    public void exportExcel() throws IOException {
        // 准备一个xls文件 [输入流]
         // 创建一个workbook[相当于xls文件]
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建一个Sheet
        HSSFSheet sheet = workbook.createSheet("分区数据");

        //创建标题行
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("分区编码");
        row.createCell(1).setCellValue("区域编码");
        row.createCell(2).setCellValue("关键字");
        row.createCell(3).setCellValue("省市区");

        // 查询数据
        List<Subarea> subareas = subareaService.findAll();
        for(Subarea s: subareas){
            row = sheet.createRow(sheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(s.getId());
            row.createCell(1).setCellValue(s.getRegion().getId());
            row.createCell(2).setCellValue(s.getAddresskey());
            row.createCell(3).setCellValue(s.getRegion().getName());
        }

        // 响应 [输出流]
        HttpServletResponse response = ServletActionContext.getResponse();

        //设置响应头
        String fileName = URLEncoder.encode("分区的数据.xls","utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        String contentType = ServletActionContext.getServletContext().getMimeType(fileName);
        response.setContentType(contentType);
//        response.setContentType("application/octet-stream");

        OutputStream os = response.getOutputStream();
        workbook.write(os);
    }

    public void listJson() throws IOException {

        //示绑定的分区数据[也就是这个分区没有指派一个员工负责]
        List<Subarea> list = subareaService.findAllWithUnbind();
        responseJson(list, new String[]{"decidedzone", "region"});

    }
}
