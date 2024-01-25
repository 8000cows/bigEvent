package example.horse.config;

import example.horse.interceptor.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by LiuSheng at 2024/1/26 2:27
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] paths = {"/user/login", "/user/register"};
        registry.addInterceptor(loginInterceptor).excludePathPatterns(paths);
    }
}
