package com.shy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shy.model.Plan;
import com.shy.model.Uplan;
import com.shy.model.Users;
import com.shy.service.PlanService;
import com.shy.service.UplanService;
import com.shy.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PlanController {
    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false,timeout = 15000)
    private UserService usersService;

    @Reference(interfaceClass = PlanService.class,version = "1.0.0",check = false,timeout = 15000)
    private PlanService planService;

    @Reference(interfaceClass = UplanService.class,version = "1.0.0",check = false,timeout = 15000)
    private UplanService uplanService;


    @RequestMapping("/allPlan")
    public String allPlan(String name){
        String flag = "err";
        List<Plan> plans = planService.selectPlan(name);
        for (int i = 0; i < plans.size(); i++) {
            if (plans.get(i).getPlanStatus().equals("0")){
                plans.get(i).setPlanStatus("进行中");
            }else {
                plans.get(i).setPlanStatus("完成了");
            }

        }
        Map<String,Object> res = new HashMap<>();
        if (plans.isEmpty()){
            res.put("flag",flag);
            return JSON.toJSONString(res);
        }
        flag = "ok";
        res.put("flag",flag);
        res.put("plan",plans);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/addPlan")
    public String addPlan(String name,Plan plan){
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
        plan.setPlanDate(plan.getPlanDate().substring(0,10));
        planService.insert(plan);
        List<Plan> plans = planService.selectTime(plan.getPlanDate());
        if (plans.isEmpty()){
            flag = "err";
            msg = "添加失败，稍后在试";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        Uplan uplan = new Uplan();
        uplan.setUid(users.getId());
        uplan.setPid(plans.get(plans.size()-1).getId());
        uplanService.insert(uplan);
        res.put("flag",flag);
        return JSON.toJSONString(res);
    }


    @RequestMapping("/delPlan")
    public String delPlan(String name,Integer id){
        Map<String,Object> res = new HashMap<>();
        String flag = "ok";
        String msg;
        List<Plan>  plans = planService.selectPlan(name);
        if (plans.isEmpty()){
            flag = "err";
            msg = "用户名错误";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        for (int i = 0; i < plans.size(); i++) {
            Plan plan = plans.get(i);
            if (plan.getId().equals(id)){
                planService.delete(id);
                uplanService.delete(id);
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

    @RequestMapping("/status")
    public String shy(String name,String status){
        Map<String,Object> res = new HashMap<>();
        String flag = "ok";
        String msg;
        List<Plan>  plans = planService.selectPlan(name);
        if (plans.isEmpty()){
            flag = "err";
            msg = "用户名错误";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        List<Plan> plans1 = new ArrayList<>();
//        根据用户查看进行的计划
        for (int i = 0; i < plans.size(); i++) {
            if (plans.get(i).getPlanStatus().equals(status)) {
                plans1.add(plans.get(i));
                plans.get(i).setPlanStatus("进行中");
            }
        }
        res.put("flag",flag);
        res.put("plans",plans1);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/status1")
    public String shy1(String name,String status){
        Map<String,Object> res = new HashMap<>();
        String flag = "ok";
        String msg;
        List<Plan>  plans = planService.selectPlan(name);
        if (plans.isEmpty()){
            flag = "err";
            msg = "用户名错误";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        List<Plan> plans1 = new ArrayList<>();
//        根据用户查看进行的计划
        for (int i = 0; i < plans.size(); i++) {
            if (plans.get(i).getPlanStatus().equals(status)) {
                plans1.add(plans.get(i));
                plans.get(i).setPlanStatus("完成了");
            }
        }
        res.put("flag",flag);
        res.put("plans",plans1);
        return JSON.toJSONString(res);
    }
}
