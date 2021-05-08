package com.shy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shy.model.Target;
import com.shy.model.Users;
import com.shy.model.Ut;
import com.shy.service.TargetService;
import com.shy.service.UserService;
import com.shy.service.UtService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TargetController {

    @Reference(interfaceClass = TargetService.class,version = "1.0.0",check = false,timeout = 15000)
    private TargetService targetService;

    @Reference(interfaceClass = UtService.class,version = "1.0.0",check = false,timeout = 15000)
    private UtService utService;

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false,timeout = 15000)
    private UserService usersService;


    @RequestMapping("/allTar")
    public String allTar(String name){
        String flag = "err";
        Map<String,Object> res = new HashMap<>();
        List<Target> targets = targetService.select(name);
        if (targets.isEmpty()){
            res.put("flag",flag);
            return JSON.toJSONString(res);
        }
        flag = "ok";
        res.put("flag",flag);
        res.put("targets",targets);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/addTar")
    public String addTar(String name,Target target){
        String flag = "err";
        String msg;
        Map<String,Object> res = new HashMap<>();
        Users users = usersService.selectUsername(name);
        if (users == null){
            msg = "用户名错误";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        target.setTarStart(target.getTarStart().substring(0,10));
        target.setTarOver(target.getTarOver().substring(0,10));
        targetService.insert(target);
        List<Target> targets = targetService.selectTime(target.getTarStart());
        if (targets.isEmpty()){
            msg = "添加失败，稍后在试";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        Ut ut = new Ut();
        ut.setUid(users.getId());
        ut.setTid(targets.get(targets.size()-1).getId());
        utService.insert(ut);
        flag = "ok";
        res.put("flag",flag);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/delTar")
    public String delTar(String name,Integer id){
        Map<String,Object> res = new HashMap<>();
        String flag = "ok";
        String msg;
        List<Target> targets = targetService.select(name);
        if (targets.isEmpty()){
            flag = "err";
            msg = "用户名错误";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        for (int i = 0; i < targets.size(); i++) {
            if (targets.get(i).getId().equals(id)) {
                targetService.delete(id);
                utService.delete(id);
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
