package service.impl;

import base.BaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.Product;
import pojo.page.ProductPage;
import service.ProductService;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceImplTest extends BaseTest {

    @Autowired
    private ProductService productService;

    @Test
    public void publishProduct() {
    }

    @Test
    public void getProductById() {
        Product product = productService.getProductById(30);
        System.out.println(JSONObject.toJSONString(product));
        assertNotNull(product);
    }

    @Test
    public void getProductsPageable() {
        ProductPage page = new ProductPage();
        page.setMinPrice(3);
        List<Product> products = productService.getProductsPageable(page);
        System.out.println(JSONObject.toJSONString(products));
    }
}