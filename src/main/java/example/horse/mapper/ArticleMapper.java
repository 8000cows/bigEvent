package example.horse.mapper;

import example.horse.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LiuSheng at 2024/1/30 9:56
 */

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(id, title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values (null, #{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    Integer insert(Article article);
}