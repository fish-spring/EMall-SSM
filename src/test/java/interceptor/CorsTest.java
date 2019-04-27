package interceptor;

import base.BaseTest;
import base.MockMvcBaseTest;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

public class CorsTest extends MockMvcBaseTest {

    @Test
    public void afterCompletion() throws Exception{
        ResultMatcher cOrigin = MockMvcResultMatchers.header()
                .string("Access-Control-Allow-Origin","*");
        ResultMatcher cMethods = MockMvcResultMatchers.header()
                .string("Access-Control-Allow-Methods","*");
        ResultMatcher cHeaders = MockMvcResultMatchers.header()
                .string("Access-Control-Allow-Headers","*");



        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/products");

        // 直接访问，应该返回所有的分类信息
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(cOrigin)
                .andExpect(cMethods)
                .andExpect(cHeaders);
    }
}