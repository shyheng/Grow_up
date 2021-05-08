package com.shy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shy.model.Diary;
import com.shy.service.DiaryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class DiaryController {

    @Reference(interfaceClass = DiaryService.class, version = "1.0.0", check = false, timeout = 15000)
    private DiaryService diaryService;

    @RequestMapping("/all")
    public String all() {
        String flag = "ok";
        Map<String, Object> res = new HashMap<>();
        List<Diary> diaries = diaryService.selectDiary();
        if (diaries.isEmpty()) {
            flag = "err";
            res.put("flag", flag);
            return JSON.toJSONString(res);
        }
        res.put("flag", flag);
        res.put("diaries", diaries);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/add")
    public String add(Diary diary) {
        diary.setDateDay(diary.getDateDay().substring(0,10));
        int i = diaryService.insertUser(diary);
        String flag = "err";
        Map<String, Object> res = new HashMap<>();
        if (i == 0) {
            res.put("flag", flag);
            return JSON.toJSONString(res);
        }
        flag = "ok";
        res.put("flag",flag);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(calendar.DATE,-30);
        Date stat = calendar.getTime();
        String s = sdf.format(stat);
        diaryService.deleteByPrimaryKey(s);
        return JSON.toJSONString(res);


    }

}
