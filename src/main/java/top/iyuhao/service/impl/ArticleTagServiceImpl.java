package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.ArticleTag;
import top.iyuhao.service.ArticleTagService;
import top.iyuhao.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【article_tag(文章标签表)】的数据库操作Service实现
* @createDate 2023-08-20 15:40:21
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{

}




