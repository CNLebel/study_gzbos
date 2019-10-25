package com.test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.gyf.crm.domain.Customer;
import com.gyf.crm.service.CustomerService;
import org.junit.Test;

public class dome04 {

    @Test
    public void test1() throws Exception{

        HessianProxyFactory factory = new HessianProxyFactory();
        CustomerService customerService = (CustomerService) factory.create(CustomerService.class, "http://localhost:8080/crm/remoting/customer");
        Customer customer = customerService.findCustomerByTel("13702475034");
        System.out.println(customer);
    }

}
