package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.TargetMapper;
import com.shy.model.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = TargetService.class,version = "1.0.0",timeout = 15000)
public class TargetServiceImpl implements TargetService {

    @Autowired
    private TargetMapper targetMapper;

    @Override
    public List<Target> select(String name) {
        return targetMapper.selectByPrimaryKey(name);
    }

    @Override
    public List<Target> selectTime(String time) {
        return targetMapper.selectTime(time);
    }

    @Override
    public int insert(Target target) {
        return targetMapper.insert(target);
    }

    @Override
    public int upData(Target target) {
        return targetMapper.updateByPrimaryKey(target);
    }

    @Override
    public int delete(Integer id) {
        return targetMapper.deleteByPrimaryKey(id);
    }
}
