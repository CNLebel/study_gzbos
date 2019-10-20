package com.gyf.bos.service;

import com.gyf.bos.model.User;
import com.gyf.bos.service.base.IBaseService;

public interface IUserService extends IBaseService<User> {

    public User findByTel(String tel);

    public User login(String username, String password);

    public void modifyPassword(String newPad, String userId);
}
