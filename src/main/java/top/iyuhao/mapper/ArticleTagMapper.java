package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【article_tag(文章标签表)】的数据库操作Mapper
* @createDate 2023-09-08 22:03:16
* @Entity top.iyuhao.entity.ArticleTag
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

}




