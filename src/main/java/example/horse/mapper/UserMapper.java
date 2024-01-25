package example.horse.mapper;

import example.horse.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
