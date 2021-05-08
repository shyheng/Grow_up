package com.shy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shy.model.Personal;
import com.shy.model.Upid;
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
public class RegeditController {

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false,timeout = 15000)
    private UserService usersService;

    @Reference(interfaceClass = PersonalService.class,version = "1.0.0",check = false,timeout = 15000)
    private PersonalService personalService;

    @Reference(interfaceClass = UpidService.class,version = "1.0.0",check = false,timeout = 15000)
    private UpidService upidService;

    @RequestMapping("/regedit")
    public String regedit(Users users, Personal personal){
        System.out.println(users);
        System.out.println(personal);
        String flag = "err";
        String msg;
        Map<String,Object> res = new HashMap<>();
        if (usersService.selectUsername(users.getUsername()) == null) {
            users.setUsername(users.getUsername());
            usersService.insertUser(users);
        }else{
            msg = "账户被使用";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        personal.setBirthday(personal.getBirthday().substring(0,10));
        personalService.insert(personal);
        Upid upid = new Upid();
        Users users2 = usersService.selectUsername(users.getUsername());
        List<Personal> pid = personalService.selectId(personal.getPhone());
        if (pid.isEmpty()){
            msg = "添加失败，稍后在试";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        upid.setPid(pid.get(pid.size()-1).getId());
        upid.setUid(users2.getId());
        upidService.insert(upid);
        flag = "ok";
        res.put("users",users2);
        res.put("flag",flag);
        return JSON.toJSONString(res);
    }
}
