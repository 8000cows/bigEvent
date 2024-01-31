package example.horse.service;

import example.horse.pojo.Article;
import example.horse.pojo.PageBean;

import java.util.List;

/**
 * Created by LiuSheng at 2024/1/30 10:11
 */

public interface ArticleService {
    Integer addArticle(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Integer updateById(Article article);

    Article getById(Integer id);

    Integer deleteById(Integer id);
}
