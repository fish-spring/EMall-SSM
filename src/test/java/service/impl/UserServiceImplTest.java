package service.impl;

import base.BaseTest;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.User;
import service.UserService;

import static org.junit.Assert.*;

public class UserServiceImplTest extends BaseTest {

    @Autowired
    UserService userService;

    @Test
    public void registryUser() {
        User user = new User();
        user.setId(1);
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setPassword("123456789");
        user = userService.registryUser(user);

        // 应该返回新的id，用户自定义的id应该被重置
        assertFalse(user.getId().equals(1));

        // 用户应该有了token
        assertNotNull(user.getToken());

        System.out.println(JSONObject.toJSONString(user));
    }

    @Test
    public void checkAndReturn() {
        String username = RandomStringUtils.randomAlphabetic(8);
        String password = "123456";

        // 先注册一个用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = userService.registryUser(user);

        assertNotNull(user.getId());

        // 然后尝试登录
        User loginUser = userService.checkAndReturn(username, password);
        // 通过ID确认登录成功
        assertEquals(user.getId(), loginUser.getId());
        System.out.println(JSONObject.toJSONString(loginUser));
    }

    @Test
    public void checkAndReturn1() {
        User loginUser = userService.checkAndReturn("qqq", "123456");
        // 错误的账号密码，返回空
        assertNull(loginUser);
    }

    @Test
    public void getInfoByUsername() {
        User user = userService.getInfoByUsername("admin");
        assertNull(user.getToken());
    }
}