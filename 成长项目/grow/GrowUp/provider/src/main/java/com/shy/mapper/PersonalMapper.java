package com.shy.mapper;

import com.shy.model.Personal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonalMapper {
    int deleteByPrimaryKey(String phone);
    int insert(Personal personal);
    int insertSelective(Personal record);
    List<Personal> selectByPrimaryKey(String username);
    List<Personal> selectId(String phone);
    int updateByPrimaryKeySelective(Personal record);
    int updateByPrimaryKey(Personal record);
}