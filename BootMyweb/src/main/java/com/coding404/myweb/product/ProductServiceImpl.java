package com.coding404.myweb.product;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("productService") //멤버변수명과 도일한 이름
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public int productRegist(ProductVO vo) {
        return productMapper.productRegist(vo);
    }

    @Override
    public ArrayList<ProductVO> getList(String prodWriter, Criteria cri) {
        return productMapper.getList(prodWriter, cri);
    }

    @Override
    public int getTotal(String prodWriter, Criteria cri) {
        return productMapper.getTotal(prodWriter,cri);
    }

    @Override
    public ProductVO getDetail(String prodId) {
        return productMapper.getDetail(prodId);
    }

    @Override
    public void productUpdate(ProductVO vo) {
        productMapper.productUpdate(vo);
    }

    @Override
    public int productDelete(String prodId) {
        return productMapper.productDelete(prodId);
    }


    @Override
    public List<CategoryVO> getCategoryList() {
        return productMapper.getCategoryList();
    }

    @Override
    public List<CategoryVO> getCategorySub(CategoryVO vo) {
       return productMapper.getCategorySub(vo);
    }
}
