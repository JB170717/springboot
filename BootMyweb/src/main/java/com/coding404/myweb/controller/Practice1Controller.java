package com.coding404.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/practice1")
public class Practice1Controller {

    @RequestMapping("/topicDetail")
    public String topicDetail() {
        return "practice1/topicDetail";
    }

    @RequestMapping("/topicListAll")
    public String topicListAll() {
        return "practice1/topicListAll";
    }

    @RequestMapping("/topicListMe")
    public String topicListMe() {
        return "practice1/topicListMe";
    }
    @RequestMapping("/topicModify")
    public String topicModify() {
        return "practice1/topicModify";
    }
    @RequestMapping("/topicReg")
    public String topicReg() {
        return "practice1/topicReg";
    }

}
