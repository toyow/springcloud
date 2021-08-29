package com.huya.springcloud.controller;

import com.huya.springcloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class HelloController {
    @Autowired
    private Environment environment;

    @RequestMapping("/hello")
    public String Hello() throws InterruptedException {
//        Integer a=1/0;
       // Thread.sleep(4000);
        return environment.getProperty("hello");
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
