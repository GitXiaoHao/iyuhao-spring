package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.BlogArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【blog_article(博客文章表)】的数据库操作Mapper
* @createDate 2023-08-28 18:00:31
* @Entity top.iyuhao.entity.BlogArticle
*/
@Mapper
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

}




