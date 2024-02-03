package example.horse.interceptor;

import example.horse.utils.JwtUtil;
import example.horse.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * Created by LiuSheng at 2024/1/26 2:24
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {
        if (HttpMethod.OPTIONS.toString().equals(req.getMethod())) {
            return true;
        }

        try {
            String token = req.getHeader("Authorization");
            Map<String, Object> map = JwtUtil.parseToken(token);

            String redisToken = stringRedisTemplate.opsForValue().get(token);

            // token失效
            if (redisToken == null) throw new RuntimeException();

            ThreadLocalUtil.set(map);
            return true;
        } catch (Exception e) {
            resp.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtil.remove();
    }
}
