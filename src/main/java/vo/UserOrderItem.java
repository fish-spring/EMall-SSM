package vo;

import lombok.Getter;
import lombok.Setter;
import pojo.Product;

// 用户提交购买记录时的数据
@Getter
@Setter
public class UserOrderItem {
    private Integer productId;
    private Integer count;
    private String shippingUsername;
    private String address;

    // 向数据库查询后保存到这里
    private Product product;
}
