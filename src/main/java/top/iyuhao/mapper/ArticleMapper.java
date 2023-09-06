package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【article(文章表)】的数据库操作Mapper
* @createDate 2023-08-20 15:40:18
* @Entity top.iyuhao.entity.Article
*/@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}




