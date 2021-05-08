package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.PersonalMapper;
import com.shy.model.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = PersonalService.class,version = "1.0.0",timeout = 15000)
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private PersonalMapper personalMapper;

    @Override
    public int insert(Personal personal) {
        return personalMapper.insert(personal);
    }

    @Override
    public List<Personal> select(String username) {
        return personalMapper.selectByPrimaryKey(username);
    }

    @Override
    public List<Personal> selectId(String phone) {
        return personalMapper.selectId(phone);
    }

    @Override
    public int delete(String phone) {
        return personalMapper.deleteByPrimaryKey(phone);
    }
}
