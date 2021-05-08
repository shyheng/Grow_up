package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.DiaryMapper;
import com.shy.model.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Service(interfaceClass = DiaryService.class,version = "1.0.0",timeout = 15000)
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    public int insertUser(Diary diary) {
        return diaryMapper.insert(diary);
    }

    @Override
    public List<Diary> selectDiary() {
        return diaryMapper.selectByPrimaryKey();
    }

    @Override
    public int deleteByPrimaryKey(String dateDay) {
        return diaryMapper.deleteByPrimaryKey(dateDay);
    }
}
