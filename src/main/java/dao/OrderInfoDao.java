package dao;

import pojo.OrderDetail;
import pojo.OrderInfo;

public interface OrderInfoDao {
    public int insertSelective(OrderInfo orderInfo);
    public int updateSelective(OrderInfo orderInfo);
}
