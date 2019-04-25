package pojo.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPage {

    // 用于Where语句
    private Integer shopId;
    private Integer categoryId;
    private Integer minPrice;
    private Integer maxPrice;
    private Date beforeDate;
    private Date afterDate;


    // 排序字段 order by
    private String orderBy;
    private Boolean desc;

    //
    private Integer limit;

    private Integer offset;
}
