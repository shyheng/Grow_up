package com.shy.mapper;

import com.shy.model.Work;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Work record);
    int insertSelective(Work record);
    List<Work> selectByPrimaryKey(String name);
    int updateByPrimaryKeySelective(Work record);
    int updateByPrimaryKey(Work work);
    List<Work> selectTime(String workTime);
}