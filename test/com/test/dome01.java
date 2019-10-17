package com.test;

import com.gyf.bos.dao.IUserDao;
import com.gyf.bos.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class dome01 {

    @Autowired
    private IUserDao userDao;

    @Test
    public void test(){
        User user = new User();
        user.setUsername("abc");
        user.setPassword("123");

        userDao.save(user);
    }
}
