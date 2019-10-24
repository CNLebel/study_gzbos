package com.gyf.bos.service.base;

import com.gyf.bos.dao.IDecidedzoneDao;
import com.gyf.bos.dao.INoticebillDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    protected INoticebillDao noticebillDao;

    @Autowired
    protected IDecidedzoneDao decidedzoneDao;

}
