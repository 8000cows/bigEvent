package example.horse.controller;

import example.horse.pojo.Result;
import example.horse.pojo.User;
import example.horse.service.UserService;
import example.horse.utils.JwtUtil;
import example.horse.utils.Md5Util;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuSheng at 2024/1/25 14:30
 */

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result<?> register(@Pattern(regexp = "^\\S{5,16}$") String username,
                              @Pattern(regexp = "^\\S{5,16}$") String password) {

        User u = userService.findByUsername(username);
        if (u != null) return Result.error("用户名已被占用...");

        Integer result = userService.register(username, password);
        return result == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");

    }

    @PostMapping("/login")
    public Result<?> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {
        User u = userService.findByUsername(username);

        if (u == null) return Result.error("用户不存在");

        if (Md5Util.getMD5String(password).equals(u.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("账号或密码错误");
    }
}
