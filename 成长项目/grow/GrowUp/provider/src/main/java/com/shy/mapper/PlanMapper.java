package com.shy.mapper;

import com.shy.model.Plan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Plan record);
    int insertSelective(Plan record);
    List<Plan> selectByPrimaryKey(String username);
    List<Plan> selectTime(String planDate);
    int updateByPrimaryKeySelective(Plan record);
    int updateByPrimaryKey(Plan record);
}