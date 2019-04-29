package pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {
    private Integer id;
    private Integer orderInfoId;
    private Integer productId;
    private Integer productPrice;
    private Integer shopId;
    private Integer userId;
    private Integer productCount;
    private String shippingUsername;
    private String address;
}

//    id int unsigned not null auto_increment comment '订单详情id',
//
//            order_info_id int unsigned not null comment '对应的订单的id',
//
//            product_id int unsigned not null comment '商品id',
//
//            -- 一个支付订单对应多个订单详情，需要有这个字段来确认
//            --   同时价格可能会发生变化
//            product_price int unsigned not null comment '购买是商品的价格',
//            shop_id int unsigned not null comment '店铺id',
//            -- 冗余设计，方便后来的店铺查询
//
//            product_count int unsigned default 1 not null comment '商品数量',
//
//        -- 这个地址是可变的，所以没有通过外键关联，它是独立的
//        shipping_username varchar(20) comment '收货人姓名',
//        address varchar(300) null comment '详细地址',