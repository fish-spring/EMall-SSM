package service.impl;

import base.BaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.ProductCategory;
import service.ProductCategoryService;
import vo.ProductCategoryVo;

import java.util.List;

import static org.junit.Assert.*;

public class ProductCategoryServiceImplTest extends BaseTest {

    @Autowired
    private ProductCategoryService service;

    @Test
    public void getInfoById() {
        ProductCategory category = service.getInfoById(100001);
        System.out.println(JSONObject.toJSONString(category));
        assertNotNull(category);
    }

    @Test
    public void selectAllByParentId() {
        List<ProductCategory> categories = service.selectAllByParentId(0);
        System.out.println(JSONObject.toJSONString(categories));
        assertNotNull(categories);
    }

    @Test
    public void selectAllByParentIdAndRecursive() {
        ProductCategoryVo vo = service.selectAllByParentIdAndRecursive(100001);
        System.out.println(JSONObject.toJSONString(vo));
        assertNotNull(vo);
    }

}