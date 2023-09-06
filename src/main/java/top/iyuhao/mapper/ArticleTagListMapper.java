package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.ArticleTagList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【article_tag_list(文章与文章标签关系表)】的数据库操作Mapper
* @createDate 2023-08-20 15:40:23
* @Entity top.iyuhao.entity.ArticleTagList
*/@Mapper
public interface ArticleTagListMapper extends BaseMapper<ArticleTagList> {

}




