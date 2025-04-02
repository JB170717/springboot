package com.simple.basic.memo.mapper;

import com.simple.basic.command.MemoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper //반드시 붙여야함
public interface MemoMapper {

    String getTime();
    void insert(MemoVO memoVO);
    ArrayList<MemoVO> getList();

}
