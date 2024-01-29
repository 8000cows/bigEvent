package example.horse.service.impl;

import example.horse.mapper.CategoryMapper;
import example.horse.service.CategoryService;
import example.horse.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
}
