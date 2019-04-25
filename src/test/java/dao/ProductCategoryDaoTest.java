package dao;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.ProductCategory;

import java.util.List;

import static org.junit.Assert.*;

public class ProductCategoryDaoTest extends DaoBaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void selectByPrimaryKey(){
        ProductCategory category = productCategoryDao.selectByPrimaryKey(100001);
        System.out.println(JSONObject.toJSONString(category));
        assertNotNull(category);
    }

    @Test
    public void selectAllByParentId(){
        List<ProductCategory> categories = productCategoryDao.selectAllByParentId(0);
        System.out.println(JSONObject.toJSONString(categories));
        assertNotNull(categories);
    }
}