use e_mall_ssm;

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id int unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',

    shop_id int unsigned not null comment '发布商品的店家id',
    category_id int unsigned NOT NULL COMMENT '分类id,对应product_category表的主键',
    name varchar(100) NOT NULL COMMENT '商品名称',
    subtitle varchar(200) COMMENT '商品副标题',
    image_url varchar(500) COMMENT '产品主图,url相对地址',
    detail text COMMENT '商品详情',
    price decimal(20,2) unsigned default 0 NOT NULL COMMENT '价格,单位-元保留两位小数',
    stock int unsigned COMMENT '库存数量',
    status tinyint unsigned DEFAULT 1 COMMENT '商品状态.1-在售 2-下架 3-删除',

    create_time datetime not null default current_timestamp() comment '创建时间',
    update_time datetime not null default current_timestamp() comment '最后一次更新时间'
      on update current_timestamp(),
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;


insert into product(shop_id, price, category_id, name, detail, stock)
values (3, 22, 10002, '方便面', null, 33);

select * from product order by price asc ;

select *
from product
where true
and price >= 0
and price < 100
-- and update_time > '1999-10-10'
and update_time < '2222-1-1'
-- and shop_id = 3
order by price desc
limit 30
offset 3
;