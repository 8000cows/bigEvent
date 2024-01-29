package example.horse.controller;

import example.horse.pojo.Result;
import example.horse.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiuSheng at 2024/1/29 12:23
 */


@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping
    public Result<?> addCategory(String categoryName, String categoryAlias) {
        Integer res = categoryService.addCategory(categoryName, categoryAlias);
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }
}
