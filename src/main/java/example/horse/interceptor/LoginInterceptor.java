package example.horse.interceptor;

import example.horse.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Created by LiuSheng at 2024/1/26 2:24
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
        try {
            String token = req.getHeader("Authorization");
            JwtUtil.parseToken(token);
            return true;
        } catch (Exception e) {
            resp.setStatus(401);
            return false;
        }
    }
}
