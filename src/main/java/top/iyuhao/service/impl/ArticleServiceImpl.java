package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.Article;
import top.iyuhao.service.ArticleService;
import top.iyuhao.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【article(文章表)】的数据库操作Service实现
* @createDate 2023-08-20 15:40:18
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




