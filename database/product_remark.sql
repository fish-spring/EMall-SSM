use e_mall_ssm;
-- 商品的评论
DROP TABLE IF EXISTS product_remark;
CREATE TABLE product_remark (
  id int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id',

  product_id int unsigned not null comment '商品的id',
  -- order_id
  user_id int unsigned not null comment '评论的的用户id',
  score tinyint unsigned not null comment '评分, 1 - 10 之间',
  content varchar(1000) comment '评论内容',

  create_time datetime not null default current_timestamp() comment '创建时间',
  update_time datetime not null default current_timestamp() comment '最后一次更新时间'
                on update current_timestamp(),
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=100032 DEFAULT CHARSET=utf8;

insert into product_remark (product_id, user_id, score, content)
values (30, 2, 8, '还行');

insert into product_remark (product_id, user_id, score, content)
values (30, 4, 7, '还行');

insert into product_remark (product_id, user_id, score, content)
values (32, 1, 2, '根本不能用！！');

insert into product_remark (product_id, user_id, score, content)
values (33, 66, 9, '非常棒');

select count(id) as salesCount, avg(score) as avgScore
from product_remark
where product_id = 30;