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
public class Product {
    private Integer id;
    private Integer shopId;
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String imageUrl;
    private String detail;
    private BigDecimal price;
    private Integer stock;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}


//    id int unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
//
//            shop_id int unsigned not null comment '发布商品的店家id',
//            category_id int unsigned NOT NULL COMMENT '分类id,对应product_category表的主键',
//            name varchar(100) NOT NULL COMMENT '商品名称',
//            subtitle varchar(200) DEFAULT NULL COMMENT '商品副标题',
//            image_url varchar(500) DEFAULT NULL COMMENT '产品主图,url相对地址',
//            detail text COMMENT '商品详情',
//            price decimal(20,2) unsigned default 0 NOT NULL COMMENT '价格,单位-元保留两位小数',
//        stock int unsigned NOT NULL COMMENT '库存数量',
//        status tinyint unsigned DEFAULT 1 COMMENT '商品状态.1-在售 2-下架 3-删除',
//
//        create_time datetime not null default current_timestamp() comment '创建时间',
//        update_time datetime not null default current_timestamp() comment '最后一次更新时间'