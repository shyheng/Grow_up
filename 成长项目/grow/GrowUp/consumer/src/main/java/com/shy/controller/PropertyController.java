package com.shy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shy.model.Property;
import com.shy.model.Uproperty;
import com.shy.model.Users;
import com.shy.service.PropertyService;
import com.shy.service.UpropertyService;
import com.shy.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PropertyController {

    @Reference(interfaceClass = PropertyService.class,version = "1.0.0",check = false,timeout = 15000)
    private PropertyService propertyService;

    @Reference(interfaceClass = UpropertyService.class,version = "1.0.0",check = false,timeout = 15000)
    private UpropertyService upropertyService;

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false,timeout = 15000)
    private UserService usersService;

    @RequestMapping("/allPro")
    public String addPro(String name){
        String flag = "err";
        Map<String,Object> res = new HashMap<>();
        List<Property> properties = propertyService.select(name);
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getPropertyType().equals("0")){
                properties.get(i).setPropertyType("微信");
            }else if (properties.get(i).getPropertyType().equals("1")){
                properties.get(i).setPropertyType("支付宝");
            }else if (properties.get(i).getPropertyType().equals("2")){
                properties.get(i).setPropertyType("银行卡");
            }else{
                properties.get(i).setPropertyType("其他");
            }

            if (properties.get(i).getIncomeExpenditure().equals("0")){
                properties.get(i).setIncomeExpenditure("收款");
            }else {
                properties.get(i).setIncomeExpenditure("支付");
            }

        }
        if (properties.isEmpty()){
            res.put("flag",flag);
            return JSON.toJSONString(res);
        }
        flag = "ok";
        res.put("flag",flag);
        res.put("properties",properties);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/addPro")
    public String addPro(String name,Property property){
        String flag = "err";
        String msg;
        Map<String,Object> res = new HashMap<>();
        Users users = usersService.selectUsername("shy");
        if (users == null){
            msg = "用户名错误";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        property.setPropertyDate(property.getPropertyDate().substring(0,10));
        propertyService.insert(property);
        List<Property> properties = propertyService.selectTime(property.getPropertyDate());
        if (properties.isEmpty()){
            msg = "添加失败，稍后在试";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        Uproperty uproperty = new Uproperty();
        uproperty.setUid(users.getId());
        uproperty.setPid(properties.get(properties.size()-1).getId());
        upropertyService.insert(uproperty);
        flag = "ok";
        res.put("flag",flag);
        return JSON.toJSONString(res);
    }

    @RequestMapping("/delPro")
    public String delPro(String name, Integer id){
        Map<String,Object> res = new HashMap<>();
        String flag = "ok";
        String msg;
        List<Property> properties = propertyService.select(name);
        if (properties.isEmpty()){
            flag = "err";
            msg = "用户名错误";
            res.put("flag",flag);
            res.put("msg",msg);
            return JSON.toJSONString(res);
        }
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getId().equals(id)){
                propertyService.delete(id);
                upropertyService.delete(id);
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
