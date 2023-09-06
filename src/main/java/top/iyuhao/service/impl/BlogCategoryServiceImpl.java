package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.BlogCategory;
import top.iyuhao.service.BlogCategoryService;
import top.iyuhao.mapper.BlogCategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yuhao
* @description 针对表【blog_category(博客分类表)】的数据库操作Service实现
* @createDate 2023-08-25 08:46:42
*/
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory>
    implements BlogCategoryService{

    @Override
    public List<BlogCategory> getAll() {
        return this.baseMapper.selectList(null);
    }
}




