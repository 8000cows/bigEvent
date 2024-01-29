package example.horse.service.impl;

import example.horse.mapper.CategoryMapper;
import example.horse.pojo.Category;
import example.horse.service.CategoryService;
import example.horse.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuSheng at 2024/1/29 12:29
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Integer addCategory(String category, String alias) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return categoryMapper.add(category, alias, id);
    }

    @Override
    public List<Category> getAllCategoriesByUserId() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return categoryMapper.selectAllByUserId(id);
    }

    @Override
    public Category getDetailById(Integer id) {
        return categoryMapper.selectDetailById(id);
    }

    @Override
    public Integer update(Category category) {
        return categoryMapper.update(category);
    }

    @Override
    public Integer deleteById(Integer id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return categoryMapper.deleteById(id, userId);
    }
}
