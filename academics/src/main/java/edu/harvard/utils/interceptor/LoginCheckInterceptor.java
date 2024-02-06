package edu.harvard.utils.interceptor;

import com.alibaba.fastjson.JSONObject;
import edu.harvard.utils.JwtUtils;
import edu.harvard.utils.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();

        // let /login request go
        if (url.contains("login")) {
            return true;
        }

        // no token in request
        String jwt = request.getHeader("token");
        if (!StringUtils.hasLength(jwt)) {
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        // parsing jwt
        try {
            Claims claims = JwtUtils.parseJwt(jwt);
            log.info("claims: {}", claims);
        } catch (Exception e) {
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        return true;

    }
}
