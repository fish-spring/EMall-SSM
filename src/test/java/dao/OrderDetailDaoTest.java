package dao;

import base.BaseTest;
import base.MockMvcBaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.OrderDetail;

import static org.junit.Assert.*;

public class OrderDetailDaoTest extends DaoBaseTest {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void insert(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderInfoId(2);
        orderDetail.setProductId(33);
        orderDetail.setProductCount(2);
        orderDetail.setProductPrice(3000);
        orderDetail.setShopId(33);
        orderDetail.setUserId(3);
        orderDetail.setShippingUsername("Jon Snow");

        orderDetailDao.insertSelective(orderDetail);
        assertNotNull(orderDetail.getId());
        System.out.println(JSONObject.toJSONString(orderDetail));
    }
}