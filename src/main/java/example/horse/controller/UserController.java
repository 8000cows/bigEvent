package example.horse.controller;

import example.horse.pojo.Result;
import example.horse.pojo.User;
import example.horse.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<?> register(@Pattern(regexp = "^\\${5,16}$") String username,
                              @Pattern(regexp = "^\\${5,16}$") String password) {

        User u = userService.findByUsername(username);
        if (u != null) return Result.error("用户名已被占用...");

        Integer result = userService.register(username, password);
        return result == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");

    }
}
