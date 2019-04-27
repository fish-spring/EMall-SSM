package pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) // 不包含为值为null的字段
public class ProductRemark {
    private Integer id;

    private Integer productId;
    private String userId;
    private Integer score;
    private String content;

    private Date createTime;
    private Date updateTime;
}


//    id int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
//
//  product_id int unsigned not null comment '商品的id',
//          user_id int unsigned not null comment '评论的的用户id',
//          score tinyint unsigned not null comment '评分, 1 - 10 之间',
//          content varchar(1000) comment '评论内容',
//
//        create_time datetime not null default current_timestamp() comment '创建时间',
//        update_time datetime not null default current_timestamp() comment '最后一次更新时间'