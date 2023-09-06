package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.BlogArticle;
import top.iyuhao.service.BlogArticleService;
import top.iyuhao.mapper.BlogArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【blog_article(博客文章表)】的数据库操作Service实现
* @createDate 2023-08-28 18:00:31
*/
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle>
    implements BlogArticleService{

}




