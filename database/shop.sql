use e_mall_ssm;

-- 使用user_id 而不是owner_id 是为了统一命名
DROP TABLE IF EXISTS shop;
CREATE TABLE shop(
  id          int unsigned NOT NULL AUTO_INCREMENT,
  user_id     int unsigned NOT NULL COMMENT '店铺创建人',
  name        varchar(30)  NOT NULL,
  description varchar(1024),
  image_url   varchar(1024),
  create_time datetime     not null default current_timestamp() comment '创建时间',
  update_time datetime     not null default current_timestamp() comment '最后一次更新时间'
    on update current_timestamp(),
  PRIMARY KEY (id)
);

insert into shop(user_id, name, description, image_url)
values (2, 'Tom 小屋', null, null);

select * from shop;