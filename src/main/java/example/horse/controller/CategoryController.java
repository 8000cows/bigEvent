package example.horse.controller;

import example.horse.pojo.Category;
import example.horse.pojo.Result;
import example.horse.service.CategoryService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by LiuSheng at 2024/1/29 12:23
 */

@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping
    public Result<?> addCategory(@RequestBody @Validated Category category) {
        Integer res = categoryService.addCategory(category.getCategoryName(), category.getCategoryAlias());
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }

    @GetMapping
    public Result<List<Category>> getAllCategoriesByUserId() {
        List<Category> categories = categoryService.getAllCategoriesByUserId();

        if (categories.size() != 0) return Result.success(categories);

        Result<List<Category>> result = new Result<>();
        result.setMessage("暂无分类");
        return result;
    }

    @GetMapping("/detail")
    public Result<Category> getDetailById(Integer id) {
        Category category = categoryService.getDetailById(id);

        if (category != null) return Result.success(category);

        Result<Category> result = new Result<>();
        result.setMessage("暂无分类");
        return result;
    }

    @PutMapping()
    public Result<?> update(@RequestBody @Validated Category category) {
        Integer res = categoryService.update(category);
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }

    @DeleteMapping
    public Result<?> deleteById(@NotNull Integer id) {
        Integer res = categoryService.deleteById(id);
        return res == 1 ? Result.success() : Result.error("网络异常, 请稍后再试...");
    }
}
