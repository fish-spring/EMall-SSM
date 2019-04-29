package util.payjs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NativeResponse {
    // 1:请求成功，0:请求失败
    private Integer return_code;

    // 发生错误是的响应信息
    private String msg;

    // 返回的消息
    private String return_msg;

    // 支付二维码地址
    private String qrcode;

    /**
     * payjs端订单号
     */
    private String payjs_order_id;

    // 用户发送的订单id
    private String out_trade_no;

    /**
     * 数据签名
     */
    private String sign;

}