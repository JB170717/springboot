package com.coding404.myweb.practice1;

import com.coding404.myweb.command.Practice1VO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Practice1Mapper {

    int regist(Practice1VO vo);
    Practice1VO getPost(String postId);
    List<Practice1VO> getListAll();

}
