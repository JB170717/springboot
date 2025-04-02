package com.simple.basic.memo.Service;

import com.simple.basic.command.MemoVO;

import java.util.ArrayList;

public interface MemoService {

    //매퍼 오토와이어드

    void regist(MemoVO memoVO);
    ArrayList<MemoVO> getList();

}
