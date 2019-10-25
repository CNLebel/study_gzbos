package com.gyf.bos.service.impl;

import com.gyf.bos.dao.IDecidedzoneDao;
import com.gyf.bos.dao.INoticebillDao;
import com.gyf.bos.dao.ISubareaDao;
import com.gyf.bos.model.*;
import com.gyf.bos.service.IDecidedzoneService;
import com.gyf.bos.service.INoticebillService;
import com.gyf.bos.service.base.BaseServiceImpl;
import com.gyf.bos.utils.BOSContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Service
@Transactional   //事务是由事务管理实现
public class NoticebillServiceImpl extends BaseServiceImpl<Noticebill> implements INoticebillService {

    @Override
    public void save(Noticebill entity) {}


    @Override
    public void save(Noticebill entity, String decidedzoneId) {

        // 添加业务通知单类型
        entity.setOrdertype("手动");  //业务通知单 自动分单 - 手动分单

        // 设置客服的id
        entity.setUser(BOSContextUtils.loginUser());

        // 调用service
        noticebillDao.save(entity);

        // 自动分单 [通过定区找到负责人]
        if(!StringUtils.isEmpty(decidedzoneId)){
            Decidedzone dz = decidedzoneDao.find(decidedzoneId);
            Staff staff = dz.getStaff();
            entity.setStaff(staff);

            // 往[工单表] 插入数据
            Workbill workbill = new Workbill();
            workbill.setType("新单"); //工单类型 新增 - 修改 - 取消
            workbill.setPickstate("未取件"); //未取件 - 取件中 - 派件中
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis())); //工单创建时间
            workbill.setAttachbilltimes(0); //取到快件的时间
            workbill.setRemark(entity.getRemark());

            // 往工单添加 业务通知单的id
            workbill.setNoticebill(entity);

            // 往工单添加 员工id
            workbill.setStaff(staff);

            workbilDaol.save(workbill);

        }



    }

    @Override
    public void delete(Noticebill entity) {

    }

    @Override
    public void update(Noticebill entity) {

    }

    @Override
    public Noticebill find(Serializable id) {
        return null;
    }

    @Override
    public List<Noticebill> findAll() {
        return null;
    }
}
