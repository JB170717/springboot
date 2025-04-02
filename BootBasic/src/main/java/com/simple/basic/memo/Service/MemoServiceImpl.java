package com.simple.basic.memo.Service;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.mapper.MemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("memoSerivce")
public class MemoServiceImpl implements MemoService {

    @Autowired
    private MemoMapper memoMapper;

    @Override
    public void regist(MemoVO memoVO) {
        memoMapper.insert(memoVO);
    }

    @Override
    public ArrayList<MemoVO> getList() {
        return memoMapper.getList();
    }
}
