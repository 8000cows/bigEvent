package example.horse.controller;

import example.horse.pojo.Article;
import example.horse.pojo.Result;
import example.horse.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LiuSheng at 2024/1/26 2:02
 */


@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping
    public Result<?> addArticle(@RequestBody @Validated Article article) {
        Integer res = articleService.addArticle(article);
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }

    @GetMapping
    public Result<?> getAllArticles() {
        return Result.success("所有的信息...");
    }
}
