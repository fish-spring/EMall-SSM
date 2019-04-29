package vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pojo.Product;

@Getter
@Setter
@NoArgsConstructor

// 给salesCount设置一个为零的默认值
// 如果没有人评价，那么avgScore的值为null, 但是我们还是想保留这个字段
//   所以我们在JSON序列化的时候没有忽略null值
public class ProductDiscount extends Product {
    private Double discount;
}
