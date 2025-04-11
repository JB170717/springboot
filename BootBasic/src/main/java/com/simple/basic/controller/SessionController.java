package com.simple.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class SessionController {

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/mypage")
    public String mypage() {
        //인증검사를 인터셉터에서 검사

        return "user/mypage";
    }

    @GetMapping("/success")
    public String success() {
        //인증검사를 인터셉터에서 검사


        return "user/success";
    }

    //로그인시도 함수
    @PostMapping("/loginForm")
    public String loginForm(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session) {
        //db에서 로그인시도..
        //select * from 유저 where username =값 and password = 값
        if(username.equals(password)){ //성공...
            //서버에서 인증수단으로 session발급
            session.setAttribute("user_id",username);
            session.setAttribute("user_name","홍길동");
            return "redirect:/user/success";
        }else{
            return "redirect:/user/login";
        }

    }

}
