package controller.user;

import base.BaseTest;
import base.MockMvcBaseTest;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pojo.User;

import static org.junit.Assert.*;

public class UsersControllerTest extends MockMvcBaseTest {

    @Test
    public void createUser() throws Exception{
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(8));

        String password = RandomStringUtils.randomAlphabetic(20);
        user.setPassword(password);

        ResultMatcher isOk = MockMvcResultMatchers.status().is(200);
        ResultMatcher hasId = MockMvcResultMatchers.jsonPath("$.id").isNumber();
        ResultMatcher hasToken = MockMvcResultMatchers.jsonPath("$.token").isNotEmpty();

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/users")
                .content(JSONObject.toJSONString(user))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(isOk)
                .andExpect(hasId)
                .andExpect(hasToken);

    }

    @Test
    public void createUserAndPasswordIsSmall() throws Exception{
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(8));

        String password = RandomStringUtils.randomAlphabetic(4);
        user.setPassword(password);

        // 因为密码太短，服务器应该拒绝创建用户
        ResultMatcher is406 = MockMvcResultMatchers.status().is(HttpStatus.NOT_ACCEPTABLE.value());

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/users")
                        .content(JSONObject.toJSONString(user))
                        .contentType(MediaType.APPLICATION_JSON);

        // 为什么没有自动检验呢？？
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(is406);

    }

    @Test
    public void getToken() throws Exception{
        String username = RandomStringUtils.randomAlphabetic(8);
        String password = RandomStringUtils.randomAlphabetic(20);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        ResultMatcher isOk = MockMvcResultMatchers.status().is(200);
        ResultMatcher hasId = MockMvcResultMatchers.jsonPath("$.id").isNumber();
        ResultMatcher hasToken = MockMvcResultMatchers.jsonPath("$.token").isNotEmpty();

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/users")
                        .content(JSONObject.toJSONString(user))
                        .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(isOk)
                .andExpect(hasId)
                .andExpect(hasToken);

        // 尝试获取token
        User loginUser = new User();
        loginUser.setPassword(password);
        MockHttpServletRequestBuilder builder1 = MockMvcRequestBuilders
                .get("/users/" + username + "/token")
                .content(JSONObject.toJSONString(loginUser))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder1)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(hasId)
                .andExpect(hasToken);
    }

    @Test
    public void getUserInfo() throws Exception{


        ResultMatcher isOk = MockMvcResultMatchers.status().is(200);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/users/admin");

        mockMvc.perform(builder)
                .andExpect(isOk)
                .andDo(MockMvcResultHandlers.print());
    }
}