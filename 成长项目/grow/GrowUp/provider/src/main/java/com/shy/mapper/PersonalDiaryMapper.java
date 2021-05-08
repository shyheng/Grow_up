package com.shy.mapper;

import com.shy.model.PersonalDiary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonalDiaryMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(PersonalDiary record);
    int insertSelective(PersonalDiary record);
    List<PersonalDiary> selectByPrimaryKey(String name);
    List<PersonalDiary> selectTime(String diaryDate);
    int updateByPrimaryKeySelective(PersonalDiary record);
    int updateByPrimaryKeyWithBLOBs(PersonalDiary record);
    int updateByPrimaryKey(PersonalDiary record);
}