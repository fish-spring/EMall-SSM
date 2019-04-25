package pojo.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductPage {

    // 用于Where语句
    private String shopId;
    private Integer minPrice;
    private Integer maxPrice;

    // 排序字段 order by
    private String orderBy;
    private Boolean desc;

    //
    private Integer limit;

    private Integer offset;
}
