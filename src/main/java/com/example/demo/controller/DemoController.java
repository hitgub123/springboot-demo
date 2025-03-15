package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Value("${aaa}")
    public String aaa;

    @Value("${bbb}")
    public String bbb;

    @Value("${ccc}")
    public String ccc;

    @RequestMapping("/a")
    public String a() {
        // aaa=aaa from properties ,bbb=bbbbbbbb ,ccc=c from cmd
        // set bbb=b from env
        // java -jar ./target/demo-0.0.1-SNAPSHOT.jar --ccc="c from cmd"
        // cmd > env > properties file
        return "aaa="+aaa+" ,bbb="+bbb+" ,ccc="+ccc;
    }
}