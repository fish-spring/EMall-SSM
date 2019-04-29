package dao;

import base.BaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.Product;
import pojo.page.ProductPage;
import vo.ProductDiscount;
import vo.ProductInfo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoTest extends BaseTest {
    @Autowired
    private ProductDao productDao;

    @Test
    public void select(){
        Product product = productDao.selectByPrimaryKey(33);
        System.out.println(JSONObject.toJSONString(product));
        assertNotNull(product);
    }

    @Test
    public void insert(){
        Product product = new Product();
        product.setShopId(33);
        product.setName("虎鞭");
        product.setStock(22);
        product.setCategoryId(100003);
        productDao.insertSelective(product);
        System.out.println(JSONObject.toJSONString(product));
        assertNotNull(product.getId());
    }

    @Test
    public void selectBtIds(){
        List<Integer> ids = new ArrayList<>();
        ids.add(31);
        ids.add(32);
        List<Product> products = productDao.selectByIds(ids);
        System.out.println(JSONObject.toJSONString(products));
        assertTrue(products.size() > 0);
    }

    @Test
    public void selectPageable(){
        ProductPage page = new ProductPage();
        page.setLimit(2);
        page.setOffset(2);
        page.setMaxPrice(3);
        List<Product> products = productDao.selectListPageable(page);
        System.out.println(JSONObject.toJSONString(products));
    }

    @Test
    public void getInfo(){
        ProductInfo info = productDao.selectProductInfoByPrimaryKey(30);
        System.out.println(JSONObject.toJSONString(info));
    }

    @Test
    public void getSlides(){
        List<Product> slides = productDao.selectSlides();
        System.out.println(JSONObject.toJSONString(slides));
    }

    @Test
    public void getSeckill(){
        List<ProductDiscount> products = productDao.selectSeckill();
        System.out.println(JSONObject.toJSONString(products));
    }
}