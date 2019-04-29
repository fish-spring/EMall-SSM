package dao;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.OrderInfo;

import static org.junit.Assert.*;

public class OrderInfoDaoTest extends DaoBaseTest {
    @Autowired
    private OrderInfoDao orderInfoDao;

    @Test
    public void insertSelective(){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(23);
        orderInfo.setPaymentMethod(4);
        orderInfo.setPaymentMoney(33);

        orderInfoDao.insertSelective(orderInfo);

        assertNotNull(orderInfo.getId());
        System.out.println(JSONObject.toJSONString(orderInfo));
    }
}