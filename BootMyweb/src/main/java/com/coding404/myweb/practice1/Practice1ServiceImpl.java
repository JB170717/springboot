package com.coding404.myweb.practice1;

import com.coding404.myweb.command.Practice1VO;
import com.coding404.myweb.product.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("practice1Service")
public class Practice1ServiceImpl implements Practice1Service {

    @Autowired
    private Practice1Mapper practice1Mapper;

    @Override
    public int regist(Practice1VO vo) {
        return practice1Mapper.regist(vo);
    }

    @Override
    public Practice1VO getPost(String postId) {
        return practice1Mapper.getPost(postId);
    }

    @Override
    public List<Practice1VO> getListAll() {
        return practice1Mapper.getListAll();
    }


}
