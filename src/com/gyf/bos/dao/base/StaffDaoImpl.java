package com.gyf.bos.dao.base;

import com.gyf.bos.dao.IStaffDao;
import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Staff;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import java.util.List;

public class StaffDaoImpl extends BaseDaoImpl<Staff> implements IStaffDao {

    @Override
    public void pageQuery(PageBean<Staff> pb) {
        //执行查询

        //查询总记录数
         // 获取离线的查询对象
        DetachedCriteria dc = pb.getDetachedCriteria();

        //select count(*) From Staff;
        //设置查询总记录数条件
        dc.setProjection(Projections.rowCount());

        List<Long> list = hibernateTemplate.findByCriteria(dc);
        Long total = list.get(0);

        pb.setTotal(total.intValue());


        //查询分页数据

        dc.setProjection(null);   //把之前条件清空
        //limit 0,10
        int start = (pb.getCurrentPage() - 1) * pb.getPageSize();
        int length = pb.getPageSize();
        List<Staff> staffs = hibernateTemplate.findByCriteria(dc, start, length);
        pb.setRows(staffs);

    }
}
