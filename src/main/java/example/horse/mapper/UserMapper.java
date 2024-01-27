package example.horse.mapper;

import example.horse.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by LiuSheng at 2024/1/25 14:27
 */

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    @Insert(" insert into user(username, password, create_time, update_time) " +
            " values (#{username}, #{password}, now(), now())")
    Integer insert(String username, String password);

    @Update("update user set nickname=#{nickname}, email=#{email}, update_time = now() where id = #{id}")
    int updateUser(User user);

    @Update("update user set user_pic = #{avatarUrl}, update_time = now() where id = #{id}")
    int updateUserPic(String avatarUrl, Integer id);

    @Update("update user set password = #{md5String}, update_time = now() where id = #{id}")
    int updatePwd(String md5String, Integer id);
}
