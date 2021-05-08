package com.shy.service;

import com.shy.model.Property;

import java.util.List;

public interface PropertyService {
    List<Property> select(String name);
    List<Property> selectTime(String date);
    int insert(Property property);
    int upDate(Property property);
    int delete(Integer id);
}
