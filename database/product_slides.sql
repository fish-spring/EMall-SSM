use e_mall_ssm;

DROP TABLE IF EXISTS product_slides;
CREATE TABLE product_slides (
    id int unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',

    product_id int unsigned not null unique ,

    create_time datetime not null default current_timestamp() comment '创建时间',
    update_time datetime not null default current_timestamp() comment '最后一次更新时间'
      on update current_timestamp(),
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

insert into product_slides(product_id)
values (32);

insert into product_slides(product_id)
values (35);

insert into product_slides(product_id)
values (37);

select product_id as id, p.image_url as imageUrl
from product_slides as slides
  inner join product as p
  on slides.product_id = p.id
;
-- 可以使用left join，但是如果数据出现异常，inner join
--   不会返回不合适的数据
