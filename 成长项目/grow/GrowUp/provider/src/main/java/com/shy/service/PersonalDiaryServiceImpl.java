package com.shy.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shy.mapper.PersonalDiaryMapper;
import com.shy.mapper.PersonalMapper;
import com.shy.model.PersonalDiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = PersonalDiaryService.class,version = "1.0.0",timeout = 15000)
public class PersonalDiaryServiceImpl implements PersonalDiaryService {

    @Autowired
    private PersonalDiaryMapper personalDiaryMapper;

    @Override
    public List<PersonalDiary> select(String naeme) {
        return personalDiaryMapper.selectByPrimaryKey(naeme);
    }

    @Override
    public int insert(PersonalDiary personalDiary) {
        return personalDiaryMapper.insert(personalDiary);
    }

    @Override
    public List<PersonalDiary> selectTime(String date) {
        return personalDiaryMapper.selectTime(date);
    }

    @Override
    public int upData(PersonalDiary personalDiary) {
        return personalDiaryMapper.updateByPrimaryKeyWithBLOBs(personalDiary);
    }

    @Override
    public int delete(Integer id) {
        return personalDiaryMapper.deleteByPrimaryKey(id);
    }
}
