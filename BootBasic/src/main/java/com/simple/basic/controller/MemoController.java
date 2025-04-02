package com.simple.basic.controller;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.Service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {

//   //멤버변수를 통한 주입
//    @Autowired
//    @Qualifier("memoSerivce")
//    private MemoService memoService;

    //생성자를 통한 주입
//    @Autowired
//    public MemoController(MemoService memoService) {
//        this.memoService = memoService;
//    }  -> 이걸 대신해주는게... @RequiredArgsConstructor -> 롬복어노테이션

    private final MemoService memoService; //반드시 final필드로 생성..


    @GetMapping ("/memoWrite")
    public String memoWrite(){
        return "memo/memoWrite";
    }

    @PostMapping("/memoForm")
    public String memoForm(@Valid @ModelAttribute("memoVO") MemoVO memoVO, BindingResult bindingResult){

        System.out.println(memoVO.toString());

        if(bindingResult.hasErrors()){
            return "memo/memoWrite";
        }

        memoService.regist(memoVO);
        return "redirect:/memo/memoList";
    }

    //메모 등록
    @GetMapping("/memoList")
    public String memoList(Model model, MemoVO memoVO){

       ArrayList<MemoVO> list = memoService.getList();
       model.addAttribute("list", list);

       return "memo/memoList";
    }

}
