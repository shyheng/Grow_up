package com.shy.mapper;

import com.shy.model.Target;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TargetMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Target record);
    int insertSelective(Target record);
    List<Target> selectByPrimaryKey(String name);
    List<Target> selectTime(String time);
    int updateByPrimaryKeySelective(Target record);
    int updateByPrimaryKey(Target record);
}