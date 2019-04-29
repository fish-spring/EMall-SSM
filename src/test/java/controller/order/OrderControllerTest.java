package controller.order;

import base.MockMvcBaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pojo.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderControllerTest extends MockMvcBaseTest {

    @Test
    public void getProduct() throws Exception{
        ResultMatcher isOk = MockMvcResultMatchers.status().is(200);

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(33);
        products.add(product);
        products.add(product);
        products.add(product);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(products));

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(isOk);
    }
}