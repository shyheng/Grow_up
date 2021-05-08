package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.PropertyMapper;
import com.shy.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = PropertyService.class,version = "1.0.0",timeout = 15000)
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public List<Property> select(String name) {
        return propertyMapper.selectByPrimaryKey(name);
    }

    @Override
    public List<Property> selectTime(String date) {
        return propertyMapper.selectTime(date);
    }

    @Override
    public int insert(Property property) {
        return propertyMapper.insert(property);
    }

    @Override
    public int upDate(Property property) {
        return propertyMapper.updateByPrimaryKey(property);
    }

    @Override
    public int delete(Integer id) {
        return propertyMapper.deleteByPrimaryKey(id);
    }
}
