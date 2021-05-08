package com.shy.mapper;

import com.shy.model.Ut;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UtMapper {
    int insert(Ut record);
    int insertSelective(Ut record);
    int delete(Integer id);
}