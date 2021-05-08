package com.shy.service;

import com.shy.model.Target;

import java.util.List;

public interface TargetService {
    List<Target> select(String name);
    List<Target> selectTime(String time);
    int insert(Target target);
    int upData(Target target);
    int delete(Integer id);
}
