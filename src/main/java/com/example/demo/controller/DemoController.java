package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.User;
import com.example.demo.service.UserService;


@RestController
public class DemoController {

    @Value("${aaa}")
    public String aaa;

    @Value("${bbb}")
    public String bbb;

    @Value("${ccc}")
    public String ccc;

    @Autowired
    private UserService userService;

    // @PostMapping("/user{id}")
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id, "zhangsan");
    }

    @GetMapping("/users/{id}")
    public List<User> getUsers(@PathVariable String id) {
        return userService.getUsersById(id);
    }

    @RequestMapping("/a")
    public String a() {
        // aaa=aaa from properties ,bbb=bbbbbbbb ,ccc=c from cmd
        // set bbb=b from env
        // java -jar ./target/demo-0.0.1-SNAPSHOT.jar --ccc="c from cmd"
        // cmd > env > properties file
        return "aaa=" + aaa + " ,bbb=" + bbb + " ,ccc=" + ccc;
    }

}