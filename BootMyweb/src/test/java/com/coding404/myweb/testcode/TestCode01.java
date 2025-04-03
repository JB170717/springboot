package com.coding404.myweb.testcode;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.ProductMapper;
import com.coding404.myweb.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCode01 {

    /*
    @Autowired
    public ProductMapper productMapper;


    @Test
    public void testCode01() {
        for(int i=1;i<=300;i++) {
           ProductVO vo = ProductVO.builder()
                   .prodEnddate("2025-06-30")
                   .prodWriter("admin")
                   .prodName("demo"+i)
                   .prodPrice(1000*i)
                   .prodCount(100*i)
                   .prodDiscount(10)
                   .prodPurchaseYn("N")
                   .prodContent("demo"+i)
                   .prodComment("demo"+i)
                   .build();
            productMapper.productRegist(vo);
        }

    }

     */
}
