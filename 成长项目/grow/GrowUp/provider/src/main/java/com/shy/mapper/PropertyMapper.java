package com.shy.mapper;

import com.shy.model.Property;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PropertyMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Property record);
    int insertSelective(Property record);
    List<Property> selectByPrimaryKey(String name);
    List<Property> selectTime(String date);
    int updateByPrimaryKeySelective(Property record);
    int updateByPrimaryKey(Property record);
}