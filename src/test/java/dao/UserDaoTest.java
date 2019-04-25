package dao;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.User;

import static org.junit.Assert.*;

public class UserDaoTest extends DaoBaseTest {
    @Autowired
    private UserDao userDao;

    // 通过主键来查询一个用户
    @Test
    public void selectByPrimaryKey(){
        User user = userDao.selectByPrimaryKey(1);
        System.out.println(JSONObject.toJSONString(user));
        assertNotNull(user);
    }

    // 查看一个邮箱是否已经被注册了
    @Test
    public void checkEmail(){
        // 已知一个邮箱是存在的
        String email = "admin@happymmall.com";
        int checkEmail = userDao.checkEmail(email);
        assertEquals(1, checkEmail);
    }

    @Test
    public void createUser(){
        User user = new User();
    }

    @Test
    public void selectLogin(){
        // 这个用户是存在的
        String username = "admin";
        String password = "123456";
        User user = userDao.selectLogin(username, password);
        assertNotNull(user);

        // 这个密码是错误的
        String password2 = "xxxxxx";
        User user2 = userDao.selectLogin(username, password2);
        assertNull(user2);
    }
}