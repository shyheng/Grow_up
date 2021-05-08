package com.shy.mapper;

import com.shy.model.Upid;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpidMapper {
    int deleteByPrimaryKey(Integer uid);
    int insert(Upid upid);
    int insertSelective(Upid record);
    Upid selectByPrimaryKey(Integer uid);
    int updateByPrimaryKeySelective(Upid record);
    int updateByPrimaryKey(Upid record);
}