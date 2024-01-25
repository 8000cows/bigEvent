package example.horse.controller;

import example.horse.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiuSheng at 2024/1/26 2:02
 */


@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping
    public Result<?> getAllArticles() {
        return Result.success("所有的信息...");
    }
}
