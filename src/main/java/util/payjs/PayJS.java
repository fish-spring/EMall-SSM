package util.payjs;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayJS {
    private String mchid;
    private String key;
    private String baseUrl = "https://payjs.cn/api";

    private OkHttpClient client = new OkHttpClient();

    public PayJS(String mchid, String key){
        this.mchid = mchid;
        this.key = key;
    }

    // https://help.payjs.cn/api-lie-biao/sao-ma-zhi-fu.html
    public NativeResponse apiNative(String out_trade_no, int total_fee) {

        // 创建请求url
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(baseUrl + "/native").newBuilder();
        urlBuilder.addQueryParameter("mchid", mchid);
        urlBuilder.addQueryParameter("out_trade_no", out_trade_no);
        urlBuilder.addQueryParameter("total_fee", String.valueOf(total_fee));

        // 获得密钥
        Map<String, String> map = new HashMap<>();
        map.put("mchid", mchid);
        map.put("out_trade_no", out_trade_no);
        map.put("total_fee", String.valueOf(total_fee));
        String sign = SignUtil.getSign(map, key);

        urlBuilder.addQueryParameter("sign", sign);
        // 获得最终的请求url
        String url = urlBuilder.build().toString();

        // get 请求也是可以的
        Request request = new Request.Builder()
                .url(url)
                .build();

        // 准备发起请求
        Call call = client.newCall(request);

        Response response = null;

        try {
            // 发起请求并接受响应
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String responseString = "";
        try {
            // 获得响应体内容
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONObject.parseObject(responseString, NativeResponse.class);
    }
}
