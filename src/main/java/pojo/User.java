package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) // 不包含为值为null的字段
public class User {
    private Integer id;

    // 创面用户的时候用户名和密码是必填项
    //   同时需要验证下长度是否合法

    @NotNull
    @Size(min = 1, max = 20, message = "名字长度应该在1-20之间")
    private String username;

    @NotNull
    @Size(min = 6, max = 50, message = "密码长度应该在6-50之间")
    private String password;

    private String email;

    private String phone;

    private String token;

    private Integer role;

    private Date createTime;

    private Date updateTime;
}
