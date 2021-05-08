package com.shy.mapper;

import com.shy.model.Uplan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UplanMapper {
    int insert(Uplan record);
    int insertSelective(Uplan record);
    int delete(Integer pid);
}