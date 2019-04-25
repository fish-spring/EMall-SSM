package controller.category;

import base.MockMvcBaseTest;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class CategoriesControllerTest extends MockMvcBaseTest {

    @Test
    public void getSingleProductCategory() throws Exception {

        ResultMatcher isOk = MockMvcResultMatchers.status().is(200);
        ResultMatcher hasCurrentCategory = MockMvcResultMatchers
                .jsonPath("$.currentCategory").exists();
        ResultMatcher hasChildren = MockMvcResultMatchers
                .jsonPath("$.children").isArray();

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/categories/product/100001");

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(isOk)
                .andExpect(hasCurrentCategory)
                .andExpect(hasChildren);
    }

    @Test
    public void getAllProductCategories() throws Exception{
        ResultMatcher isOk = MockMvcResultMatchers.status().is(200);
        ResultMatcher isArray = MockMvcResultMatchers
                .jsonPath("$").isArray();

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/categories/product");

        // 直接访问，应该返回所有的分类信息
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(isOk)
                .andExpect(isArray);
    }
}