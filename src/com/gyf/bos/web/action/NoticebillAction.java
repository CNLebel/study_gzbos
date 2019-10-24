package com.gyf.bos.web.action;

import com.gyf.bos.model.Noticebill;
import com.gyf.bos.web.action.base.BaseAction;
import com.gyf.crm.domain.Customer;

import java.io.IOException;


/**
 * 业务通知单
 * 客服接电话下快递单
 *
 * */
public class NoticebillAction extends BaseAction<Noticebill>{


    /**
     * 返回Json数据
     *
     * */
    private String tel; //电话

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void findCustomerByTel() throws IOException {
        //远程调用
        Customer customer = customerService.findCustomerByTel(tel);
        responseJson(customer, new String[]{});
    }

    private String decidedzoneId;

    public String getDecidedzoneId() {
        return decidedzoneId;
    }

    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }

    @Override
    public String save() {

//        System.out.println(getModel());
        noticebillService.save(getModel(), decidedzoneId);

        return NONE;
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
}