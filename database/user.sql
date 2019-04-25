use e_mall_ssm;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id int unsigned not null auto_increment comment '用户id',
    username varchar(10) not null comment '用户名',

    -- MD5后生成的ID比较长，所以这里搞一个比较大的数值
    password varchar(50) not null comment '用户密码，MD5加密',
    email varchar(20),
    phone varchar(20),
    token varchar(30) not null comment '用户的token',
    role tinyint not null default 1 comment '角色: 0-管理员, 1-用户',
    create_time datetime not null default current_timestamp() comment '创建时间',
    update_time datetime not null default current_timestamp() comment '最后一次更新时间'
                on update current_timestamp(),
    PRIMARY KEY (id),
    UNIQUE KEY user_name_unique (username) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- 记得插入这个记录避免测试用例出错
insert into user(username, password, email, phone, token, role)
VALUES('admin', '123456', null, null, 'qwerasdf', 0);

select * from user;