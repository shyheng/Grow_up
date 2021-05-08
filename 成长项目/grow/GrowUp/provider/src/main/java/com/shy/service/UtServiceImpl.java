package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.UtMapper;
import com.shy.model.Ut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = UtService.class,version = "1.0.0",timeout = 15000)
public class UtServiceImpl implements UtService {

    @Autowired
    private UtMapper utMapper;

    @Override
    public int insert(Ut ut) {
        return utMapper.insert(ut);
    }

    @Override
    public int delete(Integer id) {
        return utMapper.delete(id);
    }
}
