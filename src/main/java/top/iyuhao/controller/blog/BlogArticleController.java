package top.iyuhao.controller.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.*;
import top.iyuhao.dto.BlogArticleDto;
import top.iyuhao.entity.ArticleTag;
import top.iyuhao.entity.ArticleTagList;
import top.iyuhao.entity.BlogArticle;
import top.iyuhao.service.ArticleTagListService;
import top.iyuhao.service.ArticleTagService;
import top.iyuhao.service.BlogArticleService;
import top.iyuhao.utils.result.Result;
import top.iyuhao.vo.ArticleSearchDataVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuhao
 * @date 2023/8/28
 **/
@RestController
@RequestMapping("/blogArticle")
@Slf4j
public class BlogArticleController {
    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;
    @Resource
    private BlogArticleService blogArticleService;
    @Resource
    private ArticleTagListService articleTagListService;
    @Resource
    private ArticleTagService articleTagService;

    @PostMapping("/article")
    public Result getArticleByPage(@RequestParam(value = "page", defaultValue = "1") String pageStr, @RequestParam(value = "pageSize", defaultValue = "5") String pageSizeStr, @RequestBody ArticleSearchDataVo articleSearchData) {
        int page = Integer.parseInt(pageStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        if (page == 0) {
            page = 1;
        }
        if (pageSize == 0) {
            pageSize = 5;
        }
        LambdaQueryWrapper<BlogArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(articleSearchData.getTitleFuzzy() != null, BlogArticle::getBlogArticleTitle, articleSearchData.getTitleFuzzy())
                .eq(articleSearchData.getSearchStatus() != null, BlogArticle::getBlogStatusName, articleSearchData.getSearchStatus())
                .eq(articleSearchData.getSearchCategoryName() != null, BlogArticle::getBlogCategoryName, articleSearchData.getSearchCategoryName());
        Page<BlogArticle> articlePage = new Page<>(page, pageSize);
        blogArticleService.page(articlePage, wrapper);
        return Result.ok(articlePage);
    }

    @PostMapping("/addArticle")
    public Result addArticle(@RequestBody BlogArticleDto blogArticleDto) {
        //添加文章 同时需要添加标签
        //开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        boolean res = false;
        try {
            //先添加 文章
            BlogArticle blogArticle = blogArticleDto.getBlogArticle();
            blogArticleService.save(blogArticle);
            //添加标签
            res = addLabelRelationship(blogArticleDto.getTags(), blogArticle);
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            log.error(e.getMessage());
            dataSourceTransactionManager.rollback(transactionStatus);
        }
        return res ? Result.ok("success") : Result.fail("fail");
    }

    @PutMapping("/update")
    public Result updateBlogArticle(@RequestBody BlogArticleDto blogArticleDto) {
        //添加文章 同时需要添加标签
        //开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        boolean res = false;
        try {
            //先更新
            BlogArticle blogArticle = blogArticleDto.getBlogArticle();
            blogArticleService.updateById(blogArticle);
            //再删除 标签关系表
            deleteArticleTagRelationship(blogArticle);
            res = addLabelRelationship(blogArticleDto.getTags(), blogArticle);
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            log.error(e.getMessage());
            dataSourceTransactionManager.rollback(transactionStatus);
        }
        return res ? Result.ok("success") : Result.fail("fail");
    }
    @DeleteMapping
    public Result deleteArticle(@RequestBody BlogArticle blogArticle){
        //开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        boolean res = false;
        try {
            //先删除文章
            blogArticleService.removeById(blogArticle);
            //再删除 标签关系表
            deleteArticleTagRelationship(blogArticle);
            res = true;
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            log.error(e.getMessage());
            dataSourceTransactionManager.rollback(transactionStatus);
        }
        return res ? Result.ok("success") : Result.fail("fail");
    }

    /**
     * 根据文章的 id 找到文章与标签的关系
     * 操作文章 标签关系表
     *
     * @param id
     * @return
     */
    @GetMapping("/relation/{id}")
    public Result getArticleTag4relationship(@PathVariable("id") String id) {
        //找到 标签id
        LambdaQueryWrapper<ArticleTagList> articleTagListLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagListLambdaQueryWrapper.eq(ArticleTagList::getArticleId, id);
        //找到所有的 标签
        List<ArticleTagList> articleTagListList = articleTagListService.list(articleTagListLambdaQueryWrapper);
        List<String> articleTag4id = new ArrayList<>(articleTagListList.size());
        //根据标签id 找到标签名称
        articleTagListList.forEach(articleTagList -> {
            ArticleTag articleTag = articleTagService.getById(articleTagList.getArticleTagId());
            articleTag4id.add(articleTag.getArticleTagName());
        });
        return Result.ok(articleTag4id);
    }

    /**
     * 根据 文章id 删除文章与标签的对应关系
     * @param blogArticle
     * @throws Exception
     */
    private void deleteArticleTagRelationship(BlogArticle blogArticle) throws Exception{
        LambdaQueryWrapper<ArticleTagList> articleTagListLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagListLambdaQueryWrapper.eq(ArticleTagList::getArticleId, blogArticle.getBlogArticleId());
        articleTagListService.remove(articleTagListLambdaQueryWrapper);
    }

    private boolean addLabelRelationship(String[] tags, BlogArticle blogArticle) throws Exception {
        List<ArticleTagList> articleTagLists = new ArrayList<>(tags.length);
        //添加
        for (String tag : tags) {
            //找到标签
            LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
            articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleTagName, tag);
            ArticleTag articleTag = articleTagService.getOne(articleTagLambdaQueryWrapper);
            //添加到 关系表
            ArticleTagList articleTagList = new ArticleTagList();
            articleTagList.setArticleTagId(articleTag.getArticleTagId());
            articleTagList.setArticleId(blogArticle.getBlogArticleId());
            articleTagLists.add(articleTagList);
        }
        articleTagListService.saveBatch(articleTagLists);
        return true;
    }
}
