package com.test;

import com.gyf.bos.dao.IFunctionDao;
import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Dome05 {

    @Autowired
    private RuntimeService rs;

    @Test
    public void test(){
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("bxyy","出差路费报销");
        info.put("bxje","2688.00");
        info.put("employeeName","listi");
        rs.startProcessInstanceByKey("bxlc",info);
    }

}
