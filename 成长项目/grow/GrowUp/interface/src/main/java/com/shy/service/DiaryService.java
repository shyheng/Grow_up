package com.shy.service;

import com.shy.model.Diary;

import java.util.Date;
import java.util.List;


public interface DiaryService {
    int insertUser(Diary diary);
    List<Diary> selectDiary();
    int deleteByPrimaryKey(String dateDay);
}
