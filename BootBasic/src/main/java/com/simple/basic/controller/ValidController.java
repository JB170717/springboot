package com.simple.basic.controller;

import com.simple.basic.command.DemoVO;
import com.simple.basic.command.ValidVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/valid")
public class ValidController {

    //화면처리
    @GetMapping("/view")
    public String view() {
        return "valid/view";
    }

    //결과화면처리
    @GetMapping("/result")
    public String result() {
        return "valid/result";
    }

    //가입요청
    @PostMapping("/joinForm")
    public String joinForm(@Valid @ModelAttribute("vo") ValidVO vo, BindingResult result, Model model) {
        //유효성검사를 진행하고, 실패한 목록을 bindingresult에 담아줍니다.

//        //1st
//        if(result.hasErrors()) { //유효성검사에 실패하면 true
//           List<FieldError> list= result.getFieldErrors(); //실패한 목록
//
//            for(FieldError err:list){
//
//                System.out.println(err.getField()); //실패한 필드명
//                System.out.println(err.getDefaultMessage()); //실패한 필드의 message
//
//                model.addAttribute("valid_"+err.getField(), err.getDefaultMessage());
//            }
//
//            return "valid/view";
//        }
        //2nd
        if (result.hasErrors()) {
            return "valid/view";
        }

        //처리
        return "redirect:/valid/result";
    }

    /// ///////////////////////////////////////////////////////////

    @GetMapping("/quiz01")
    public String quiz01(){
        return "valid/quiz01";
    }

    @PostMapping("/quiz01Form")
    public String quiz01Form(@Valid  @ModelAttribute("vo") DemoVO vo, BindingResult result,
                             RedirectAttributes redirectAttributes) {

        System.out.println("id:"+vo.getId());
        System.out.println("pw:"+vo.getPw());

        if(result.hasErrors()){
            return "valid/quiz01";
        }

        redirectAttributes.addFlashAttribute("vo", vo);

        return "redirect:/valid/quiz01_result";
    }

    @GetMapping("/quiz01_result")
    public String quiz01Result(){
        return "valid/quiz01_result";
    }

}
