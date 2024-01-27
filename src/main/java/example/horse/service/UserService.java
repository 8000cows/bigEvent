package example.horse.service;

import example.horse.pojo.User;

/**
 * Created by LiuSheng at 2024/1/25 14:26
 */

public interface UserService {
    User findByUsername(String username);
    Integer register(String username, String password);

    Integer updateUserInfo(User user);

    Integer updateAvatar(String avatarUrl);

    int updatePwd(String md5String);
}
