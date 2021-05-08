package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.UpropertyMapper;
import com.shy.model.Uproperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UpropertyService.class,version = "1.0.0",timeout = 15000)
public class UpropertyServiceImpl implements UpropertyService {

    @Autowired
    private UpropertyMapper upropertyMapper;

    @Override
    public int insert(Uproperty uproperty) {
        return upropertyMapper.insert(uproperty);
    }

    @Override
    public int delete(Integer id) {
        return upropertyMapper.delete(id);
    }
}
