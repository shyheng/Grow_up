package com.shy.mapper;

import com.shy.model.Uproperty;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpropertyMapper {
    int insert(Uproperty record);
    int insertSelective(Uproperty record);
    int delete(Integer id);
}