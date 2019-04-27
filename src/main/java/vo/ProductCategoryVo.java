package vo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pojo.ProductCategory;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) // 不包含为值为null的字段
public class ProductCategoryVo extends ProductCategory {
    private List<ProductCategoryVo> children;

    public static ProductCategoryVo getInstance(ProductCategory category){
        String categoryJSONString = JSONObject.toJSONString(category);
        return  JSONObject.parseObject(categoryJSONString, ProductCategoryVo.class);
    }
}
