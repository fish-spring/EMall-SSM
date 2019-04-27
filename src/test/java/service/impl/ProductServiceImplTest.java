package service.impl;

import base.BaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.Product;
import pojo.page.ProductPage;
import service.ProductService;
import vo.ProductVo;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceImplTest extends BaseTest {

    @Autowired
    private ProductService productService;

    @Test
    public void publishProduct() {
    }

    @Test
    public void getProductVoById() {
        ProductVo productVo = productService.getProductVoById(30);
        System.out.println(JSONObject.toJSONString(productVo));
        assertNotNull(productVo);
    }

    @Test
    public void getProductVosPageable() {
        ProductPage page = new ProductPage();
        page.setMaxPrice(3);
        List<ProductVo> products = productService.getProductVosPageable(page);
        System.out.println(JSONObject.toJSONString(products));
    }

    @Test
    public void getProductVos() {
        //List<ProductVo> productVos = productService.getProductById()
    }
}