package pojo;

// CREATE TABLE shop(
//  id          int unsigned NOT NULL AUTO_INCREMENT,
//  user_id     int unsigned NOT NULL COMMENT '店铺创建人',
//  name        varchar(30)  NOT NULL,
//  description varchar(1024),
//  image_url   varchar(1024),
//  create_time datetime     not null default current_timestamp() comment '创建时间',
//  update_time datetime     not null default current_timestamp() comment '最后一次更新时间'
//    on update current_timestamp(),
//  PRIMARY KEY (id)
//);

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
public class Shop {
    private Integer id;

    private Integer userId;
    private String name;
    private String description;
    private String imageUrl;

    private Date createTime;
    private Date updateTime;
}
