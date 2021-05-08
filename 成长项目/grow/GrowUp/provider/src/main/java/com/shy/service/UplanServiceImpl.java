package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.UplanMapper;
import com.shy.model.Uplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UplanService.class,version = "1.0.0",timeout = 15000)
public class UplanServiceImpl implements UplanService {

    @Autowired
    private UplanMapper uplanMapper;

    @Override
    public int insert(Uplan uplan) {
        return uplanMapper.insert(uplan);
    }

    @Override
    public int delete(Integer id) {
        return uplanMapper.delete(id);
    }
}
