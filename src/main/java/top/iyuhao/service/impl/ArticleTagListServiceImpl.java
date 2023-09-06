package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.ArticleTagList;
import top.iyuhao.service.ArticleTagListService;
import top.iyuhao.mapper.ArticleTagListMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【article_tag_list(文章与文章标签关系表)】的数据库操作Service实现
* @createDate 2023-08-20 15:40:23
*/
@Service
public class ArticleTagListServiceImpl extends ServiceImpl<ArticleTagListMapper, ArticleTagList>
    implements ArticleTagListService{

}




