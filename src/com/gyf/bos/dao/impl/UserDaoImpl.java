package com.gyf.bos.dao.impl;

import com.gyf.bos.dao.IUserDao;
import com.gyf.bos.dao.base.BaseDaoImpl;
import com.gyf.bos.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {




    @Override
    public User findByEmail(String email) {
        return null;
    }
}
