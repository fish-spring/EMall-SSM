use e_mall_ssm;

DROP TABLE IF EXISTS product_seckill;
CREATE TABLE product_seckill (
  id int unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
  product_id int unsigned not null unique ,
  discount double unsigned not null ,

  create_time datetime not null default current_timestamp() comment '创建时间',
  update_time datetime not null default current_timestamp() comment '最后一次更新时间'
    on update current_timestamp(),
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

insert into product_seckill(product_id, discount)
values (32, 0.3),
       (35, 0.8),
       (37, 0.4);

select product_id as id, discount, shop_id, price, category_id, name, detail, stock
from product_seckill as seckill
         inner join product as p
                    on seckill.product_id = p.id
;
-- 可以使用left join，但是如果数据出现异常，inner join
--   不会返回不合适的数据
select product_id as id, discount, shop_id, price, category_id, name, detail, stock
from product_seckill as seckill
       inner join product as p
                  on seckill.product_id = p.id
;