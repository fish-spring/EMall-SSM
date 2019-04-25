package controller.product;

import base.BaseTest;
import base.MockMvcBaseTest;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
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

public class ProductsControllerTest extends MockMvcBaseTest {

    @Test
    public void getProducts() throws Exception{


        ResultMatcher isOk = MockMvcResultMatchers.status().is(200);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/products")
                        .param("minPrice", "2");

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(isOk);
    }
}