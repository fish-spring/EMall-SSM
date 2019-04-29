package dao;

import pojo.OrderDetail;
import pojo.OrderInfo;

public interface OrderDetailDao {
    public int insertSelective(OrderDetail orderDetail);
}
