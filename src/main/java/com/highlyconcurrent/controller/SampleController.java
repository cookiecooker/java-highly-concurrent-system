package com.highlyconcurrent.controller;

import com.highlyconcurrent.domain.User;
import com.highlyconcurrent.redis.RedisService;
import com.highlyconcurrent.redis.UserKey;
import com.highlyconcurrent.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.highlyconcurrent.result.Result;

@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    RedisService redisService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    @ResponseBody
    public String thymeleaf(Model model) {
        model.addAttribute("name", "here");
        return "hello";
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {

        User user = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById, "" + 1, user);
        return Result.success(true);
    }
}
