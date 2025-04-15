package com.example.jpa;

import com.example.jpa.entity.Memo;
import com.example.jpa.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JPAJpql04 {

    @Autowired
    private MemoRepository memoRepository;
    @Autowired
    private Memo memo;

    @Test
    public void testCode01() {
        List<Memo> list=memoRepository.getList();
        System.out.println(list.toString());
    }

    @Test
    public void testCode02() {
        List<Memo> list=memoRepository.getList2(200L);
        System.out.println(list.toString());
    }

    @Test
    public void testCode03() {
        List<Object[]> list = memoRepository.getlist3("3");
        for (Object[] arr : list) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testCode04() {
        int result = memoRepository.updateMemo("바꿀값",100L);
        System.out.println("업데이트 수행결과:"+result);
    }

    @Test
    public void testCode05() {
        Memo m = Memo.builder()
                .mno(100L)
                .writer("객체파라미터")
                .text("객체파라미터")
                .build();
        int result = memoRepository.updateMemo2(m);
        System.out.println("업데이트 수행결과:"+result);
    }

    @Test
    public void testCode06() {
        int result = memoRepository.deleteMemo(177L);
        System.out.println("업데이트 수행결과:"+result);
    }

    @Test
    public void testCode07() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<Memo> page = memoRepository.getListPage(100L, pageable);
        System.out.println(page.getContent().toString());
    }

    @Test
    public void testCode08() {
       Memo memo = memoRepository.getNative(20L);
        System.out.println(memo);
    }




}
