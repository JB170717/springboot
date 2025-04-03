package com.coding404.myweb.controller;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

//    //화면처리 기본유형
//    @GetMapping("/productList")
//    public String productList(Model model) {
//
//        String prodWriter ="admin";
//        ArrayList<ProductVO> list = productService.getList(prodWriter);
//
//        model.addAttribute("list", list);
//        log.info(list.toString());
//
//        return "product/productList";
//    }

    @GetMapping("/productList")
    public String productList(Model model, Criteria cri) {

        String prodWriter ="admin";
        ArrayList<ProductVO> list = productService.getList(prodWriter, cri); //데이터
        int total = productService.getTotal(prodWriter); //토탈
        PageVO pageVO = new PageVO(cri,total); //페이지네이션

        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVO);

        return "product/productList";
    }



    @GetMapping("/productReg")
    public String productReg() {
        return "product/productReg";
    }

    @GetMapping("/productDetail")
    public String productDetail(@RequestParam("prodId") String prodId,
                                Model model) {



        //prodId를 받아서 조회
        ProductVO vo = productService.getDetail(prodId);

        model.addAttribute("vo", vo);

        return "product/productDetail";
    }

    //등록기능
    @PostMapping("/registForm")
    public String registForm(ProductVO vo, RedirectAttributes ra) {

        log.info(vo.toString());

        int result = productService.productRegist(vo);
        //1이면 성공, 0이면 실패 1회성으로 메세지를 줘보자.

        if(result == 1) {
            ra.addFlashAttribute("msg","정상 등록되었습니다.");
        }else{
            ra.addFlashAttribute("msg","시스템문제로 인해 등록에 실패했습니다\n155-1000으로 문의주세요.");
        }

        return "redirect:/product/productList";
    }

    //수정기능
    @PostMapping("/updateForm")
    public String updateForm(ProductVO vo){

        log.info(vo.toString());

        productService.productUpdate(vo);

        //상세화면(Detail)이 prodId를 필요로 하기 때문에...
        return "redirect:/product/productDetail?prodId="+vo.getProdId(); //수정하고 컨텐츠화면으로
    }

    //삭제기능
    /*
    1. 화면에서는 deleteForm으로 삭제요청이 넘어옵니다. (데이터는 다 넘어옴~)
    2. productDelte메서드를 이용해서 삭제를 진행하면 됩니다.
    3. 삭제한 이후에는 성공시 성공 msg를 보내주세요.
        삭제 목록으로 넘어가면 됩니다.
        삭제 실패시 실패msg를 보내주면됩니다.

    */
    @PostMapping("/deleteForm")
    public String deleteForm(@RequestParam("prodId") String prodId,
                             RedirectAttributes ra) {

       int result =productService.productDelete(prodId);

       if(result == 1) {
           ra.addFlashAttribute("msg","삭제되었습니다");
       }else {
           ra.addFlashAttribute("msg", "삭제실패");
       }

        return "redirect:/product/productList";
    }

}
