package example.horse.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LiuSheng at 2024/1/29 12:24
 */

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(id, category_name, category_alias, create_user, create_time, update_time) " +
            "values(null, #{name}, #{alias}, #{userId}, now(), now())")
    Integer add(String name, String alias, Integer userId);
}
