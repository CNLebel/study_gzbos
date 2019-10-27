package com.gyf.bos.service.base;

import com.gyf.bos.dao.*;
import com.gyf.bos.service.IWorkordermanageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    protected INoticebillDao noticebillDao;

    @Autowired
    protected IDecidedzoneDao decidedzoneDao;

    @Autowired
    protected IWorkbilDaol workbilDaol;

    @Autowired
    protected IWorkordermanageDao workordermanageDao;

    @Autowired
    protected IFunctionDao functionDao;
}
