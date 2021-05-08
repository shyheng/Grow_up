package com.shy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shy.model.PersonalDiary;
import com.shy.model.Ud;
import com.shy.model.Users;
import com.shy.service.PersonalDiaryService;
import com.shy.service.UdService;
import com.shy.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DiaryPersonController {

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false,timeout = 15000)
    private UserService usersService;

    @Reference(interfaceClass = PersonalDiaryService.class,version = "1.0.0",check = false,timeout = 15000)
    private PersonalDiaryService personalDiaryService;

    @Reference(interfaceClass = UdService.class,version = "1.0.0",check = false,timeout = 15000)
    private UdService udService;

    @RequestMapping("allDia")
    public String allDia(String name){
        Map<String,Object> res = new HashMap<>();
        String flag = "err";
        List<PersonalDiary> personalDiaries = personalDiaryService.select(name);
        if (personalDiaries.isEmpty()){
            res.put("flag",flag);
            return JSON.toJSONString(res);
        }
        flag = "ok";
        res.put("flag",flag);
        res.put("personalDiaries",personalDiaries);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/addDia")
    public String addDia(String name,PersonalDiary personalDiary){
        Map<String,Object> res = new HashMap<>();
        String flag = "ok";
        String msg;
        Users users = usersService.selectUsername(name);
        if (users == null){
            flag = "err";
            msg = "用户名错误";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        personalDiary.setDiaryDate(personalDiary.getDiaryDate().substring(0,10));
        personalDiaryService.insert(personalDiary);
        List<PersonalDiary> personalDiaries = personalDiaryService.selectTime(personalDiary.getDiaryDate());
        if (personalDiaries.isEmpty()){
            flag = "err";
            msg = "添加失败，稍后在试";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        Ud ud = new Ud();
        ud.setUid(users.getId());
        ud.setDid(personalDiaries.get(personalDiaries.size()-1).getId());
        udService.insert(ud);
//        File file2 = new File(personalDiary.getDiaryFile());
//        MultipartFile file = (MultipartFile) file2;
//        if (file == null) {
//            flag = "err";
//            msg = "上传失败，稍后在试";
//            res.put("flag",flag);
//            res.put("msg",msg);
//            return JSON.toJSONString(res);
//        }
//        File file1 = new File("E:\\GrowUp\\consumer\\src\\main\\resources\\static\\");
//        try {
//            file.transferTo(file1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        res.put("flag",flag);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/delDia")
    public String delDia(String name,Integer id){
        Map<String,Object> res = new HashMap<>();
        String flag = "ok";
        String msg;
        List<PersonalDiary> personalDiaries = personalDiaryService.select("shy");
        if (personalDiaries.isEmpty()){
            flag = "err";
            msg = "用户名错误";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        for (int i = 0; i < personalDiaries.size(); i++) {
            PersonalDiary personalDiary = personalDiaries.get(i);
            if (personalDiary.getId().equals(id)) {
                personalDiaryService.delete(id);
                udService.delete(id);
                res.put("flag",flag);
                return JSON.toJSONString(res);
            }
        }
        msg = "没有这个ID";
        flag = "err";
        res.put("msg",msg);
        res.put("flag",flag);
        return JSON.toJSONString(res);
    }
}
