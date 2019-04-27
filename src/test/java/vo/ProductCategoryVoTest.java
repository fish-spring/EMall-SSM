package vo;

import base.BaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pojo.ProductCategory;

import static org.junit.Assert.*;

public class ProductCategoryVoTest{

    @Test
    public void getInstance() {
        ProductCategory category = new ProductCategory();
        category.setId(33);
        category.setName("Jon");
        ProductCategoryVo categoryVo = ProductCategoryVo.getInstance(category);
        System.out.println(JSONObject.toJSONString(categoryVo));
    }
}