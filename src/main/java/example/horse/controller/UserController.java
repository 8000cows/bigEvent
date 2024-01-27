package example.horse.controller;

import example.horse.pojo.Result;
import example.horse.pojo.User;
import example.horse.service.UserService;
import example.horse.utils.JwtUtil;
import example.horse.utils.Md5Util;
import example.horse.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/userInfo")
    public Result<User> getCurrentUserInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    @PutMapping("/updateUserInfo")
    public Result<?> updateUserInfo(@RequestBody @Validated User user) {
        int res = userService.updateUserInfo(user);
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }

    @PatchMapping("/updateAvatar")
    public Result<?> updateAvatar(@URL String avatarUrl) {
        int res = userService.updateAvatar(avatarUrl);
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }

    @PatchMapping("/updatePwd")
    public Result<?> updatePwd(@RequestBody Map<String, String> pwdMap) {
        String oldPwd = pwdMap.get("old_pwd");
        String newPwd = pwdMap.get("new_pwd");
        String rePwd = pwdMap.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd) ) return Result.error("参数个数错误");

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        String oldPwdMd5 = Md5Util.getMD5String(oldPwd);

        if (!oldPwdMd5.equals(user.getPassword())) return Result.error("原密码错误");

        if (!newPwd.equals(rePwd)) return Result.error("两次输入的密码不一样");

        String newPwdMd5 = Md5Util.getMD5String(newPwd);
        int res = userService.updatePwd(newPwdMd5);
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }
}
