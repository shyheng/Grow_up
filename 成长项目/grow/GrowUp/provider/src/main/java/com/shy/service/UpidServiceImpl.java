package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.UpidMapper;
import com.shy.model.Upid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UpidService.class,version = "1.0.0",timeout = 15000)
public class UpidServiceImpl implements UpidService {

    @Autowired
    private UpidMapper upidMapper;

    @Override
    public int insert(Upid upid) {
        return upidMapper.insert(upid);
    }
}
