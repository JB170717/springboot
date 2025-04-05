package com.coding404.myweb.practice1;


import com.coding404.myweb.command.Practice1VO;

import java.util.List;

public interface Practice1Service {

    int regist(Practice1VO vo);
    Practice1VO getPost(String postId);
    List<Practice1VO> getListAll();

}

