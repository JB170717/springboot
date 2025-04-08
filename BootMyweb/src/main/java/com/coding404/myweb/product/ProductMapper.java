package com.coding404.myweb.product;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ProductMapper {

    int productRegist(ProductVO vo);
    //ArrayList<ProductVO> getList(String prodWriter);
    ArrayList<ProductVO> getList(@Param("prodWriter") String prodWriter,
                                 @Param("cri") Criteria cri);
    int getTotal(@Param("prodWriter") String prodWriter,
                 @Param("cri") Criteria cri);

    ProductVO getDetail(String prodId);
    void productUpdate(ProductVO vo);
    int productDelete(String prodId);

    //카테고리
    List<CategoryVO> getCategoryList(); //1단 select
    List<CategoryVO> getCategorySub(CategoryVO vo);



}
