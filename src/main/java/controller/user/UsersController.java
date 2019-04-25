package controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pojo.User;
import service.UserService;
import util.ErrorMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return null;
    }

    @PostMapping
    public Object createUser(@Validated @RequestBody User user, BindingResult bindingResult){
        // 用户传入的参数不合法，就直接拒接请求
        //   注册时候需要传入用户名以及密码，这里通过@Validated自动认证了
        if (bindingResult.hasErrors()){
            Map errResponse = new HashMap<String, Object>();
            errResponse.put("message", bindingResult.getAllErrors());
            // 406: 请求的资源的内容特性无法满足请求头中的条件，因而无法生成响应实体，该请求不可接受
            return ResponseEntity.status(406).body(errResponse);

        } else {
            // 可能插入数据库时可能发生异常，所以这里的返回值可能是null，
            //   至于为什么插入失败，原因太多了，这里不细说
            user = userService.registryUser(user);
            if (user == null){
                ErrorMessage.getErrorResponse(500, "无法插入到数据库");
            }

            // 如果一切正常，那么这里的user就应该携带id和token返回给用户了。
            return user;
        }
    }

    // 在REST API中，获得token并在接下来的请求中携带token就算是登录了
    //   获得token时，需要提交账号以及密码
    //   为了方便编码，提交的时候按照user来处理

    // 这里实际上任务如果用户尝试获得token，逻辑上说明用户丢失了以前的token
    //   所以这里实际上是创建了新的token并返回，权限上要求用户提供密码。
    @GetMapping("/{username}/token")
    public Object getToken(@RequestBody HashMap<String, String> validation, @PathVariable String username){
        // 这里就不用使用@Validated来验证了，我们的Service层会自己验证
        // 其实只要user中的password，但是为了编码方便，就是用这种方式来解析。

        User user = userService.checkAndReturn(username, validation.get("password"));
        if(user == null){
            return ErrorMessage.getErrorResponse(403, "密码不匹配");
        }
        return user;
    }

    @GetMapping("/{username}")
    public Object getUserInfo(@PathVariable String username){

        User user = userService.getInfoByUsername(username);
        if(user == null){
            return ErrorMessage.getErrorResponse(403, "用户名不存在或者密码不匹配");
        }
        return user;
    }
}
