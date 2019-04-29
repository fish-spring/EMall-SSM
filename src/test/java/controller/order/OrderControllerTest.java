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
import vo.UserOrderItem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderControllerTest extends MockMvcBaseTest {

    @Test
    public void buyProducts() throws Exception{
        ResultMatcher isOk = MockMvcResultMatchers.status().is(200);

        List<UserOrderItem> orderItems = new ArrayList<>();

        UserOrderItem item = new UserOrderItem();
        item.setAddress("WinterFall");
        item.setCount(1);
        item.setProductId(33);
        item.setShippingUsername("Jon Snow");
        orderItems.add(item);
        UserOrderItem item2 = new UserOrderItem();
        item2.setAddress("WinterFall");
        item2.setCount(2);
        item2.setProductId(30);
        item2.setShippingUsername("Aray Stark");
        orderItems.add(item2);


        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/orders")
                        .header("Authorization","bearer kEHNWjRqKwIO39xd6FYi0vR0sOUKBe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONObject.toJSONString(orderItems));

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(isOk);
    }
}