package com.shy.mapper;

import com.shy.model.Uw;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UwMapper {
    int delete(Integer wid);
    int insert(Uw record);
    int insertSelective(Uw record);
    Uw selectByPrimaryKey(Integer uid);
    int updateByPrimaryKeySelective(Uw record);
    int updateByPrimaryKey(Uw record);
}