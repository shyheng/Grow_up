package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.PlanMapper;
import com.shy.model.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = PlanService.class,version = "1.0.0",timeout = 15000)
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanMapper planMapper;

    @Override
    public List<Plan> selectPlan(String name) {
        return planMapper.selectByPrimaryKey(name);
    }

    @Override
    public int insert(Plan plan) {
        return planMapper.insert(plan);
    }

    @Override
    public List<Plan> selectTime(String time) {
        return planMapper.selectTime(time);
    }

    @Override
    public int upData(Plan plan) {
        return planMapper.updateByPrimaryKey(plan);
    }

    @Override
    public int delete(Integer id) {
        return planMapper.deleteByPrimaryKey(id);
    }

}
