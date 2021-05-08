package com.shy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shy.model.Personal;
import com.shy.model.Users;
import com.shy.service.PersonalService;
import com.shy.service.UpidService;
import com.shy.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserPersonalController {

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false,timeout = 15000)
    private UserService usersService;

    @Reference(interfaceClass = PersonalService.class,version = "1.0.0",check = false,timeout = 15000)
    private PersonalService personalService;

    @Reference(interfaceClass = UpidService.class,version = "1.0.0",check = false,timeout = 15000)
    private UpidService upidService;

    @RequestMapping("/allPer")
    public String allPer(String name){
        String flag = "err";
        Map<String,Object> res = new HashMap<>();
        List<Personal> personals = personalService.select(name);
        if (personals.isEmpty()){
            res.put("flag",flag);
            return JSON.toJSONString(res);
        }
        flag = "ok";
        res.put("flag",flag);
        res.put("personals",personals);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/upPer")
    public String upPer(Users users){
        String flag = "err";
        Map<String,Object> res = new HashMap<>();
        int i =  usersService.upData(users);
        if (i == 0){
            res.put("flag",flag);
            res.put("msg","更改失败");
            return JSON.toJSONString(res);
        }
        res.put("flag","ok");
        return JSON.toJSONString(res);
    }

    @RequestMapping("/delPer")
    public String delPer(String name,String phone){
        String flag = "err";
        Map<String,Object> res = new HashMap<>();
        int i = usersService.deleteUser(name);
        if (i == 0){
            res.put("flag",flag);
            res.put("msg","用户删除失败");
            return JSON.toJSONString(res);
        }
        int j =  personalService.delete(phone);
        if (j == 0){
            res.put("flag",flag);
            res.put("msg","信息删除失败");
            return JSON.toJSONString(res);
        }
        flag = "ok";
        res.put("flag",flag);
        return JSON.toJSONString(res);
    }
}
