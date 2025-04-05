package com.coding404.myweb.controller;

import com.coding404.myweb.command.Practice1VO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.practice1.Practice1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/practice1")
public class Practice1Controller {

    @Autowired
    @Qualifier("practice1Service")
    private Practice1Service practice1Service;

    @RequestMapping("/topicReg")
    public String topicReg() {

        return "practice1/topicReg";
    }

    @PostMapping("/registForm")
    public String registForm(Practice1VO vo, RedirectAttributes ra) {

        int result = practice1Service.regist(vo);

        if (result == 1) {
            ra.addFlashAttribute("msg", "게시글등록성공");
        } else {
            ra.addFlashAttribute("msg", "게시글등록실패");
        }

        System.out.println(vo.getPostId());


        return "redirect:/practice1/topicDetail?postId="+vo.getPostId();
    }

    @RequestMapping("/topicDetail")
    public String topicDetail(@RequestParam("postId") String postId, Model model) {

        Practice1VO vo = practice1Service.getPost(postId);

        model.addAttribute("vo", vo);

        return "practice1/topicDetail";
    }

    @RequestMapping("/topicListAll")
    public String topicListAll(Model model) {

        List<Practice1VO> list =practice1Service.getListAll();

        model.addAttribute("list", list);

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

}
