package interceptor;

import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Verifier implements HandlerInterceptor {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("Authorization");
        if (auth == null){
            return true;
        }
        // "bearer sfasf"  --> ["bearer", "sfasf"]
        String a[] = auth.split("\\s+");
        if (a.length == 2){
            String token = a[1];
            User user = userDao.selectByToken(token);
            if(user != null){
                request.setAttribute("VerifiedUsername",user);
                return true;
            }
        }
        // 用户信息不对，直接拒绝，哪怕后续的url不需要
        //   这样可以让前端更早的发现问题
        response.setStatus(401);
        return false;
    }
}
