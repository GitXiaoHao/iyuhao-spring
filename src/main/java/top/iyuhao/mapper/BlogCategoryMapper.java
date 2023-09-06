package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.BlogCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【blog_category(博客分类表)】的数据库操作Mapper
* @createDate 2023-08-25 08:46:42
* @Entity top.iyuhao.entity.BlogCategory
*/
@Mapper
public interface BlogCategoryMapper extends BaseMapper<BlogCategory> {

}




