package com.coding404.myweb.testcode;

import com.coding404.myweb.command.DemoMemberVO;
import com.coding404.myweb.command.DemoOrderVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestCode02 {

    @Autowired
    private TestMapper testmapper;


    @Test
    public void testCode01() {
        List<DemoOrderVO> list = testmapper.manyToOne();
        System.out.println(list.toString());
    }

    @Test
    public void testCode02() {
        DemoMemberVO vo =testmapper.oneToMany();
        System.out.println(vo.toString());
    }

}
