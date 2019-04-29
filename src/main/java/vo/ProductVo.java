package vo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pojo.Product;
import pojo.ProductCategory;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

// 给salesCount设置一个为零的默认值
// 如果没有人评价，那么avgScore的值为null, 但是我们还是想保留这个字段
//   所以我们在JSON序列化的时候没有忽略null值
public class ProductVo extends Product {
    private Integer salesCount = 0;
    private Integer remarkCount;
    private Double avgScore;
    private String shopName;

    public void getInfoFromProductInfo(ProductInfo info){
        this.avgScore = info.getAvgScore();
        this.remarkCount = info.getRemarkCount();
        this.shopName = info.getShopName();
    }

    public static ProductVo getInstance(Product product){
        String productJSONString = JSONObject.toJSONString(product);
        return  JSONObject.parseObject(productJSONString, ProductVo.class);
    }
}
