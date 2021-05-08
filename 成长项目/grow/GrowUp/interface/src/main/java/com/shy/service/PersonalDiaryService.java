package com.shy.service;

import com.shy.model.PersonalDiary;

import java.util.List;

public interface PersonalDiaryService {
    List<PersonalDiary> select(String naeme);
    int insert(PersonalDiary personalDiary);
    List<PersonalDiary> selectTime(String date);
    int upData(PersonalDiary personalDiary);
    int delete(Integer id);
}
