package pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) // 不包含为值为null的字段
public class ProductCategory {
    private Integer id;

    private Integer parentId;
    private String name;
    private Integer status;
    private Integer priority;

    private Date createTime;
    private Date updateTime;
}