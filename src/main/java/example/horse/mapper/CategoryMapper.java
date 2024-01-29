package example.horse.mapper;

import example.horse.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by LiuSheng at 2024/1/29 12:24
 */

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(id, category_name, category_alias, create_user, create_time, update_time) " +
            "values(null, #{name}, #{alias}, #{userId}, now(), now())")
    Integer add(String name, String alias, Integer userId);

    @Select("select id, category_name, category_alias, create_time, update_time from category where create_user = #{userId}")
    List<Category> selectAllByUserId(Integer userId);

    @Select("select id, category_name, category_alias, create_time, update_time from category where id = #{id}")
    Category selectDetailById(Integer id);

    @Update("update category set category_name=#{categoryName}, category_alias=#{categoryAlias}, update_time = now()" +
            " where id = #{id}")
    Integer update(Category category);

    @Delete("delete from category where id = #{id} and create_user = #{userId}")
    Integer deleteById(Integer id, Integer userId);
}
