use e_mall_ssm;
-- 测试用例启动时依赖这些数据，
--   请务必插入以保证测试用例的通过

insert into user(username, password, email, phone, token, role)
VALUES('aaa', '123456', 'aaa@bitfishxyz.com', null, 'qwerasdf', 0);