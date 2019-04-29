package controller.topic;

import base.BaseTest;
import base.MockMvcBaseTest;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

public class TopicControllerTest extends MockMvcBaseTest {

    @Test
    public void getSlides() throws Exception{
        ResultMatcher isOk = MockMvcResultMatchers.status().is(200);
        ResultMatcher jsonPath = MockMvcResultMatchers
                .jsonPath("$").isArray();


        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/topics/slides");

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(isOk)
                .andExpect(jsonPath);
    }

    @Test
    public void getSeckill() throws Exception{
        ResultMatcher isOk = MockMvcResultMatchers.status().is(200);
        ResultMatcher jsonPath = MockMvcResultMatchers
                .jsonPath("$[0].discount").isNumber();


        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/topics/seckill");

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(isOk)
                .andExpect(jsonPath);
    }
}