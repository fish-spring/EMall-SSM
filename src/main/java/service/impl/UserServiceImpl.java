package service.impl;

import dao.UserDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User registryUser(User user) {
        // id应该由数据库创建，这里防止客户端上传了id
        // 虽然mapper文件以及过滤了，但是双重保险稳一点。
        user.setId(null);

        // 密码应该使用md5加密，保证安全
        String md5Password = DigestUtils.md2Hex(user.getPassword());
        user.setPassword(md5Password);

        // 自动生成用户的token
        String token = RandomStringUtils.randomAlphanumeric(30);
        user.setToken(token);

        // insert之后，通过MyBatis的generateKey
        //    user此时应该有了ID
        // 使用selective，以免覆盖数据库的默认值
        int res = userDao.insertSelective(user);

        // 可能因为各种原因失败
        //   比如字段太长了，有重复的。。。这里做个判断
        if (res != 1){
            return null;
        }

        // 返回之前，将用户密码隐藏
        //  不隐藏也没有关系，反正是MD5加密的
        user.setPassword(null);

        return user;
    }

    public User checkAndReturn(String username, String password) {
        // 用户密码都是MD5加密的，这里同样要转化一下
        String md5Password = DigestUtils.md2Hex(password);
        return userDao.selectLogin(username, md5Password);
    }

    public User getInfoByUsername(String username){
        User user = userDao.getInfoByUsername(username);
        // 清空密码和token
        user.setPassword(null);
        user.setToken(null);
        return user;
    }
}
