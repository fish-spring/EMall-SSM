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
        // 数据库可能发生变化，不能保证测试总是成功
        // 这里的测试只是开发的时候看下结果
        //   不做异常验证
        User user = userDao.selectByPrimaryKey(33);
        System.out.println(JSONObject.toJSONString(user));
    }

    // 通过token来查询一个用户
    @Test
    public void selectByToken(){
        User user = userDao.selectByToken("kEHNWjRqKwIO39xd6FYi0vR0sOUKBe");
        System.out.println(JSONObject.toJSONString(user));
    }

    // 查看一个邮箱是否已经被注册了
    @Test
    public void checkEmail(){
        // 已知一个邮箱是存在的
        String email = "admin@bitfishxyz.com";
        int checkEmail = userDao.checkEmail(email);
        // assertEquals(1, checkEmail);
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