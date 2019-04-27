package vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pojo.Product;

@Getter
@Setter
@NoArgsConstructor
public class ProductInfo extends Product {
    private Integer salesCount;
    private Integer remarkCount;
    private Double avgScore;
}
