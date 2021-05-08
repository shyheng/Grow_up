package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.UdMapper;
import com.shy.model.Ud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UdService.class,version = "1.0.0",timeout = 15000)
public class UdServiceImpl implements UdService {

    @Autowired
    private UdMapper udMapper;

    @Override
    public int insert(Ud ud) {
        return udMapper.insert(ud);
    }

    @Override
    public int delete(Integer id) {
        return udMapper.delete(id);
    }
}
