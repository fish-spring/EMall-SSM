package pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInfo {
    private Integer id;
    private Integer userId;
    private Integer paymentMoney;
    private Integer paymentMethod;

    private String paymentOrderId;
    private String qrcode;
    private Integer isSuccess;
}


//  id int unsigned not null auto_increment comment '订单id',
//  user_id int unsigned not null comment '购买用户的id',
//  payment_money int unsigned not null comment '支付金额,单位为分',
//  payment_method tinyint unsigned not null comment '1: 网银 2：支付宝 4： payjs',
//
//  payment_order_id char(30) unique comment '支付平台下发的订单号',
//  qrcode varchar(200) comment '收款二维码的地址',
//
//  is_success tinyint default 0 not null comment '0:false  1:true 用户是否支付了',
