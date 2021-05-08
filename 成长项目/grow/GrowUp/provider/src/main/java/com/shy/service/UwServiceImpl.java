package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.UwMapper;
import com.shy.model.Uw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UwService.class,version = "1.0.0",timeout = 15000)
public class UwServiceImpl implements UwService {

    @Autowired
    private UwMapper uwMapper;

    @Override
    public int insert(Uw uw) {
        return uwMapper.insert(uw);
    }

    @Override
    public int deleteuw(Integer id) {
        return uwMapper.delete(id);
    }
}
