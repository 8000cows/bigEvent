package example.horse.service.impl;

import example.horse.mapper.ArticleMapper;
import example.horse.pojo.Article;
import example.horse.service.ArticleService;
import example.horse.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by LiuSheng at 2024/1/30 10:12
 */

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public Integer addArticle(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        return articleMapper.insert(article);
    }
}
