package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.BlogCategoryType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【blog_category_type(博客分类类型表)】的数据库操作Mapper
* @createDate 2023-09-07 23:23:06
* @Entity top.iyuhao.entity.BlogCategoryType
*/
@Mapper
public interface BlogCategoryTypeMapper extends BaseMapper<BlogCategoryType> {

}




