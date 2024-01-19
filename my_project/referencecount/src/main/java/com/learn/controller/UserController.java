package com.learn.controller;

import com.learn.pojo.Result;
import com.learn.pojo.User;
import com.learn.service.UserService;
import com.learn.utils.JwtUtil;
import com.learn.utils.Md5Util;
import com.learn.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password){
        //满足用户名和密码的要求
        if(username!=null&&username.length()>=5&&username.length()<=16
        &&password!=null&&password.length()>=5&&password.length()<=16) {
            //查询用户是否被占用
            User u = userService.findByUserName(username);
            if (u == null) {
                //没有占用并注册
                userService.register(username, password);
                return Result.success();
            } else {
                return Result.error("用户名已被占用");
            }
        }else {
            return Result.error("用户名或密码不符合规范");
        }
    }

    @PostMapping("/login")
    public Result <String> login(String username,String password){
        //根据用户名查询用户
        User loginUser=userService.findByUserName(username);
        if(loginUser==null){
            return Result.error("用户名不存在");
        }
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            //登录成功
            Map<String,Object>claims=new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token= JwtUtil.genToken(claims);
            return Result.success(token);
        }
            return Result.error("密码错误");
    }

    @GetMapping("/info")
    public Result<User>userinfo(@RequestHeader(name="Authorization")String token){
        //根据用户名查询用户
//        Map<String,Object> map=JwtUtil.parseToken(token);
//        String username=(String) map.get("username");
        Map<String,Object> map= ThreadLocalUtil.get();
        String username=(String) map.get("username");
        User user=userService.findByUserName(username);
        return Result.success(user);
    }
}
