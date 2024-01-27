package example.horse.service.impl;

import example.horse.mapper.UserMapper;
import example.horse.pojo.User;
import example.horse.service.UserService;
import example.horse.utils.Md5Util;
import example.horse.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public Integer updateUserInfo(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return userMapper.updateUserPic(avatarUrl, id);
    }

    @Override
    public int updatePwd(String md5String) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return userMapper.updatePwd(md5String, id);
    }
}
