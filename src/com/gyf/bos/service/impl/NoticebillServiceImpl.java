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
import java.util.List;


@Service
@Transactional   //事务是由事务管理实现
public class NoticebillServiceImpl extends BaseServiceImpl<Noticebill> implements INoticebillService {

    @Override
    public void save(Noticebill entity) {}


    @Override
    public void save(Noticebill entity, String decidedzoneId) {

        // 添加定单类型
        entity.setOrdertype("新增");  //定单类型， 新增 - 取消

        // 设置客服的id
        entity.setUser(BOSContextUtils.loginUser());

        // 自动分单 [通过定区找到负责人]
        if(!StringUtils.isEmpty(decidedzoneId)){
            Decidedzone dz = decidedzoneDao.find(decidedzoneId);
            Staff staff = dz.getStaff();
            entity.setStaff(staff);
        }



        // 调用service
        noticebillDao.save(entity);
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
