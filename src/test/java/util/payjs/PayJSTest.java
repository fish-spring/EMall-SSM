package util.payjs;

import base.BaseTest;
import base.MockMvcBaseTest;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PayJSTest extends BaseTest {

    @Autowired
    private PayJS payJS;

    @Test
    public void apiNative() {
        NativeResponse response = payJS.apiNative("123", 2);
        System.out.println(JSONObject.toJSONString(response));

        assertEquals(1, response.getReturn_code().intValue());
    }
}