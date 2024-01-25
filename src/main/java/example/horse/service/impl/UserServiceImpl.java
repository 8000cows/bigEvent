package example.horse.service.impl;

import example.horse.mapper.UserMapper;
import example.horse.pojo.User;
import example.horse.service.UserService;
import example.horse.utils.Md5Util;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by LiuSheng at 2024/1/25 14:28
 */

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Integer register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        return userMapper.insert(username, md5String);
    }
}
