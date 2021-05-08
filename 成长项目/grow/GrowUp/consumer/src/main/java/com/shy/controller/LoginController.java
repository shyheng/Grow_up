package com.shy.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shy.model.Users;
import com.shy.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Reference(interfaceClass = UserService.class,version = "1.0.0",check = false,timeout = 15000)
    private UserService usersService;

    @RequestMapping("/login")
    public String Login(@RequestBody Users user){
        String flag = "ok";
        Users users =  usersService.selectUsername(user.getUsername());
        Map<String,Object> res = new HashMap<>();
        if (users == null){
            res.put("msg","无效账号");
            flag = "error";
            res.put("flag",flag);
            return JSON.toJSONString(res);
        }
        if (!users.getPassword().equals(user.getPassword())){
            res.put("msg","密码错误");
            flag = "error";
            res.put("flag",flag);
            return JSON.toJSONString(res);
        }
        res.put("flag",flag);
        res.put("user",users);
        return JSON.toJSONString(res);
    }

    //    @RequestMapping("/shy")
//    public String shy(MultipartFile  file, Model model){
//        if (file == null) {
//            return "shy";
//        }
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
//        String s =  simpleDateFormat.format(date);
//        File file1 = new File("E:\\GrowUp\\consumer\\src\\main\\resources\\static\\"+s+file.getOriginalFilename());
//        try {
//            model.addAttribute("msg",file1);
//            file.transferTo(file1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("ok");
//        return "shy";
//    }
}
