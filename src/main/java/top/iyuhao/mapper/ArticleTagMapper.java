package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【article_tag(文章标签表)】的数据库操作Mapper
* @createDate 2023-08-20 15:40:21
* @Entity top.iyuhao.entity.ArticleTag
*/@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

}




