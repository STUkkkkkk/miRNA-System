package stukk.config;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import stukk.Pojo.User;
import stukk.exception.ServiceException;
import stukk.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {


    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token"); //获取token
        //执行认证
        if(StrUtil.isBlank(token)){
            System.out.println("无token 请重新登录");
            throw new ServiceException("401","token异常，请重新登录");
        }
        //获取token中的数据
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        }
        catch (JWTDecodeException j){
            throw new ServiceException("401","token异常，请重新登录");
        }

        //查询一下
        User user = (User) userService.getById(Long.parseLong(userId)).getData();
        if(user == null){
            throw new ServiceException("401","token异常，请重新登录");
        }
        //用户密码加签验证token

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try{
            jwtVerifier.verify(token);
        }
        catch (JWTVerificationException e){
            throw new ServiceException("401","token异常，请重新登录");
        }
        UserHolder.saveUser(user);
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserHolder.removeUser();
    }
}
