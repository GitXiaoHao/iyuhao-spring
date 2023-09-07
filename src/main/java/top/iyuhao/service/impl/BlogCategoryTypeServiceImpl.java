package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.BlogCategoryType;
import top.iyuhao.service.BlogCategoryTypeService;
import top.iyuhao.mapper.BlogCategoryTypeMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【blog_category_type(博客分类类型表)】的数据库操作Service实现
* @createDate 2023-09-07 23:23:06
*/
@Service
public class BlogCategoryTypeServiceImpl extends ServiceImpl<BlogCategoryTypeMapper, BlogCategoryType>
    implements BlogCategoryTypeService{

}




