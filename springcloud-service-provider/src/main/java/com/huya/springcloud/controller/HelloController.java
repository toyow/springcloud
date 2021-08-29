package com.huya.springcloud.controller;

import com.huya.springcloud.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class HelloController {
    @RequestMapping("/hello")
    public String Hello() {
        return "hello provider2";
    }

    @RequestMapping("/user")
    public User User() {
        User user = new User(1, "wudongyang1");
        return user;
    }

    @RequestMapping("/userById")
    public User UserById(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        User user = new User(id, name);
        return user;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        User user = new User(id, name);
        return user;
    }

}
