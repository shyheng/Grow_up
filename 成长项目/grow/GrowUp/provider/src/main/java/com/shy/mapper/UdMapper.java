package com.shy.mapper;

import com.shy.model.Ud;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UdMapper {
    int insert(Ud record);
    int insertSelective(Ud record);
    int delete(Integer pid);
}