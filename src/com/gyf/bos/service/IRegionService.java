package com.gyf.bos.service;

import com.gyf.bos.model.PageBean;
import com.gyf.bos.model.Region;
import com.gyf.bos.service.base.IBaseService;

import java.util.List;

public interface IRegionService extends IBaseService<Region> {

    public void saveAll(List<Region> regions);

}
