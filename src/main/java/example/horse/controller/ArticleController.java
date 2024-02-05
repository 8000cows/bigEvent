package example.horse.controller;

import example.horse.anno.State;
import example.horse.pojo.Article;
import example.horse.pojo.PageBean;
import example.horse.pojo.Result;
import example.horse.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiuSheng at 2024/1/26 2:02
 */

@Validated
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping
    public Result<?> addArticle(@RequestBody @Validated Article article) {
        Integer res = articleService.addArticle(article);
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }

    @GetMapping
    public Result<PageBean<Article>> list(@RequestParam Integer pageNum,
                                          @RequestParam Integer pageSize,
                                          String category,
                                          @State String state) {
        PageBean<Article> articles = articleService.list(pageNum, pageSize, category, state);
        return Result.success(articles);
    }

    @GetMapping("/detail")
    public Result<Article> getDetail(@RequestParam Integer id) {
        Article article = articleService.getById(id);
        return Result.success(article);
    }

    @PutMapping
    public Result<?> updateById(@RequestBody @Validated(Article.Update.class) Article article) {
        Integer res = articleService.updateById(article);
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }

    @DeleteMapping
    public Result<?> deleteById(@RequestParam Integer id) {
        Integer res = articleService.deleteById(id);
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }
}
