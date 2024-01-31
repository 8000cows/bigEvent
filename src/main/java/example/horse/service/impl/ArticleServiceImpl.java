package example.horse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import example.horse.mapper.ArticleMapper;
import example.horse.pojo.Article;
import example.horse.pojo.PageBean;
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

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> articlePageBean = new PageBean<>();

        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        Page<Article> page = (Page<Article>) articleMapper.list(categoryId, state, userId);
        articlePageBean.setTotal(page.getTotal());
        articlePageBean.setItems(page.getResult());
        return articlePageBean;
    }

    @Override
    public Integer updateById(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        return articleMapper.updateById(article);
    }

    @Override
    public Article getById(Integer id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return articleMapper.selectById(id, userId);
    }


    @Override
    public Integer deleteById(Integer id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return articleMapper.deleteById(id, userId);
    }
}
