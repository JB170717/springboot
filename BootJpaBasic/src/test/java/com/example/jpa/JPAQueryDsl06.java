package com.example.jpa;

import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPAQueryDsl06 {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testCode1(){
        Memo m = memoRepository.selectDsl();
        System.out.println(m);
    }

    @Test
    public void testCode2(){
        List<Memo> list = memoRepository.selectDsl2();
        System.out.println(list.toString());
    }

    //동적 쿼리
    @Test
    public void testCode3(){
        List<Memo> list =
                //memoRepository.selectDsl3("writer", "5"); //writer검색 5가 들어가 있는거.
                memoRepository.selectDsl3("text","6");

        System.out.println(list.toString());
    }

}
