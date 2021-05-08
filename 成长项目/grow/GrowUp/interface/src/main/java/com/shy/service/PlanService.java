package com.shy.service;

import com.shy.model.Plan;

import java.util.List;

public interface PlanService {
    List<Plan> selectPlan(String name);
    int insert(Plan plan);
    List<Plan> selectTime(String time);
    int upData(Plan plan);
    int delete(Integer id);
}
