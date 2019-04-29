use e_mall_ssm;

-- 加入购物车
-- 开始支付
--  支付完成后，创建订单以及订单详情

create table cart(
  id int unsigned not null ,

  user_id int unsigned not null comment '用户id',
  product_id int unsigned not null comment '商品id',
  product_count smallint unsigned not null comment '商品数量',
  price decimal(8, 2) not null comment '加入购物车时的订单',

  create_time datetime not null default current_timestamp() comment '创建时间',
  update_time datetime not null default current_timestamp() comment '最后一次更新时间'
    on update current_timestamp(),
  primary key (id)
);

-- 用户点击支付按钮后
--    生成订单和订单详情，is_success为false

--   用户扫码支付后将is_success设置为true

drop table if exists order_info;
create table order_info(
  id int unsigned not null auto_increment comment '订单id',
  user_id int unsigned not null comment '购买用户的id',
  payment_money int unsigned not null comment '支付金额,单位为分',
  payment_method tinyint unsigned not null comment '1: 网银 2：支付宝 4： payjs',

  payment_order_id char(30) unique comment '支付平台下发的订单号',
  qrcode varchar(200) comment '收款二维码的地址',

  is_success tinyint default 0 not null comment '0:false  1:true 用户是否支付了',

  create_time datetime not null default current_timestamp() comment '创建时间',
  update_time datetime not null default current_timestamp() comment '最后一次更新时间'
    on update current_timestamp(),
  PRIMARY KEY (id)
);
-- 一个支付信息对应对个订单详情

insert into order_info
  (user_id, payment_money, payment_method, payment_order_id, qrcode)
values (23, 999, 4, '2019042912021800646471842', 'https://payjs.cn/qrcode/d2VpeGluOi8vd3hwYXkvYml6cGF5dXJsP3ByPXoxZlU0eWI=')
;
select * from order_info;

drop table order_detail;
create table order_detail(
    id int unsigned not null auto_increment comment '订单详情id',

    order_info_id int unsigned not null comment '对应的订单的id',

    product_id int unsigned not null comment '商品id',

    -- 一个支付订单对应多个订单详情，需要有这个字段来确认
    --   同时价格可能会发生变化
    product_price int unsigned not null comment '购买是商品的价格',
    shop_id int unsigned not null comment '店铺id',
    -- 冗余设计，方便后来的店铺查询

    product_count int unsigned default 1 not null comment '商品数量',

    -- 这个地址是可变的，所以没有通过外键关联，它是独立的
    shipping_username varchar(20) comment '收货人姓名',
    address varchar(300) null comment '详细地址',

    primary key (id)
);