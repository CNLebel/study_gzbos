package com.gyf.bos.web.action;

import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Region;
import com.gyf.bos.service.IRegionService;
import com.gyf.bos.utils.PinYin4jUtils;
import com.gyf.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegionAction extends BaseAction<Region> {



    private File excelFile;

    public void setExcelFile(File excelFile) {
        this.excelFile = excelFile;
    }

    public String importExcel() throws Exception {

        //解释excel文件中数据
        //创建Workbook

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(excelFile));

        //获取sheet
        Sheet sheet = workbook.getSheetAt(0);

        //遍历里面的数据
        List<Region> regions = new ArrayList<Region>();
        for(Row row:sheet){
            String id = row.getCell(0).getStringCellValue();
            String province  = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();

            //根据中文生成城市编码
            String citycode = StringUtils.join(PinYin4jUtils.stringToPinyin(city),"");

            //根据中文生成简码
            String cityTmp = city.substring(0, city.length()-1);
            String districtTmp = district.substring(0, district.length()-1);

            String[] cityStrs = PinYin4jUtils.getHeadByString(cityTmp);
            String[] districtStrs = PinYin4jUtils.getHeadByString(districtTmp);

            String shortcode = StringUtils.join(cityStrs, "") + StringUtils.join(districtStrs, "");

            //封装成Region模型
            Region region = new Region(id, province, city, district, postcode);
            region.setCitycode(citycode);
            region.setShortcode(shortcode);
            regions.add(region);
        }

        //移除第一行的标题数据
        regions.remove(0);

        //调用service
        regionService.saveAll(regions);

        //响应
        responseStr("success");

        return SUCCESS;
    }

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
        regionService.pageQuery(pb);

        //返回json数据
        responseJson(pb, new String[]{"currentPage","pageSize","detachedCriteria","subareas"});

    }

    public void listJson() throws IOException {
        // 查询所有区域
        List<Region> regions = regionService.findAll();

        // 返回json数据
        responseJson(regions, new String[]{"subareas"});

    }
}
