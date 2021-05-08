package com.shy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shy.model.Users;
import com.shy.model.Uw;
import com.shy.model.Work;
import com.shy.service.UserService;
import com.shy.service.UwService;
import com.shy.service.WorkService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WorkController {
    @Reference(interfaceClass = WorkService.class, version = "1.0.0", check = false, timeout = 15000)
    private WorkService workService;

    @Reference(interfaceClass = UwService.class, version = "1.0.0", check = false, timeout = 15000)
    private UwService uwService;

    @Reference(interfaceClass = UserService.class, version = "1.0.0", check = false, timeout = 15000)
    private UserService usersService;


    @RequestMapping("/allWork")
    public String allWork(String name) {
        String flag = "err";
        List<Work> works = workService.select(name);
        Map<String, Object> res = new HashMap<>();
        if (works.isEmpty()) {
            res.put("flag", flag);
            return JSON.toJSONString(res);
        }
        flag = "ok";
        res.put("flag", flag);
        res.put("works", works);
        return JSON.toJSONString(res);
    }


    @RequestMapping("/addWork")
    public String addWork(String name, Work work) {
        Map<String, Object> res = new HashMap<>();
        String flag = "ok";
        String msg;
        Users users = usersService.selectUsername(name);
        if (users == null) {
            flag = "err";
            msg = "用户名错误";
            res.put("flag", flag);
            res.put("msg", msg);
            return JSON.toJSONString(res);
        }
        work.setWorkTime(work.getWorkTime().substring(0, 10));
        workService.insert(work);
        List<Work> works = workService.selectTime(work.getWorkTime());
        if (works.isEmpty()) {
            flag = "err";
            msg = "添加失败，稍后在试";
            res.put("flag", flag);
            res.put("msg", msg);
            return JSON.toJSONString(res);
        }
        Uw uw = new Uw();
        uw.setUid(users.getId());
        uw.setWid(works.get(works.size() - 1).getId());
        uwService.insert(uw);
        res.put("flag", flag);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/delWork")
    public String delWork(String name, Integer id) {
        Map<String, Object> res = new HashMap<>();
        String flag = "ok";
        String msg;
        List<Work> works = workService.select(name);
        if (works.isEmpty()) {
            flag = "err";
            msg = "用户名错误";
            res.put("flag", flag);
            res.put("msg", msg);
            return JSON.toJSONString(res);
        }
        for (int i = 0; i < works.size(); i++) {
            Work work = works.get(i);
            if (work.getId().equals(id)) {
                workService.delete(id);
                uwService.deleteuw(id);
                res.put("flag", flag);
                return JSON.toJSONString(res);
            }
        }
        msg = "没有这个ID";
        flag = "err";
        res.put("msg", msg);
        res.put("flag", flag);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/dateWork")
    public String dateWork(String name, String date) {
        String date1 = date.substring(0, 10);
        Map<String, Object> res = new HashMap<>();
        String flag = "ok";
        String msg;
        List<Work> works = workService.select(name);
        List<Work> works1 = new ArrayList<>();
        for (int i = 0; i < works.size(); i++) {
            if (works.get(i).getWorkTime().equals(date1)) {
                works1.add(works.get(i));
            }
        }
        if (works1.isEmpty()) {
            msg = "今天没有作息，可以好好休息";
            flag = "err";
            res.put("msg", msg);
            res.put("flag", flag);
            return JSON.toJSONString(res);
        }
        res.put("flag",flag);
        res.put("work",works1);
        return JSON.toJSONString(res);
    }
}
