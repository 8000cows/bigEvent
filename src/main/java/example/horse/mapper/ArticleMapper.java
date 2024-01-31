package example.horse.mapper;

import example.horse.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by LiuSheng at 2024/1/30 9:56
 */

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(id, title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values (null, #{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    Integer insert(Article article);

    List<Article> list(Integer categoryId, String state, Integer userId);

    @Update(" update article set title = #{title}, content = #{content}, cover_img = #{coverImg}," +
            " state = #{state}, category_id = #{categoryId}, update_time = now()" +
            " where create_user = #{createUser} and id = #{id}")
    Integer updateById(Article article);

    @Select("select * from article where id = #{id} and create_user = #{userId}")
    Article selectById(Integer id, Integer userId);

    @Delete("delete from article where id = #{id} and create_user = #{userId}")
    Integer deleteById(Integer id, Integer userId);
}
