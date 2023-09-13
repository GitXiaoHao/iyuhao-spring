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
import top.iyuhao.entity.*;
import top.iyuhao.service.*;
import top.iyuhao.utils.result.Result;
import top.iyuhao.vo.ArticleSearchDataVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Resource
    private BlogCategoryService blogCategoryService;
    @Resource
    private BlogSpecialService blogSpecialService;
    @Resource
    private UserService userService;

    @GetMapping("/detail/{id}")
    public Result getArticleById(@PathVariable("id") String id){
        return Result.ok(userService.getById(id));
    }
    @PostMapping("/article")
    public Result getArticleByPageFuzzy(@RequestParam(value = "page", defaultValue = "1") String pageStr, @RequestParam(value = "pageSize", defaultValue = "5") String pageSizeStr, @RequestBody ArticleSearchDataVo articleSearchData) {
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

    @GetMapping("/list")
    public Result getArticleByList() {
        return Result.ok(blogArticleService.list());
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
            //添加分类 的博客数量 专题的博客数量
            String blogCategoryName = blogArticle.getBlogCategoryName();
            updateBlogCategoryCount(blogCategoryName,1);
            String blogSpecialId = blogArticle.getBlogSpecialId();
            updateBlogSpecialCount(blogSpecialId,1);
            String userId = blogArticle.getUserId();
            updateUserCount(userId,1);
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
            //需要修改 分类 专题 博客数量
            BlogArticle beforeBlogArticle = blogArticleService.getById(blogArticle.getBlogArticleId());
            updateCount(beforeBlogArticle, blogArticle);
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
    public Result deleteArticle(@RequestBody BlogArticle blogArticle) {
        //开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        boolean res = false;
        try {
            //先删除文章
            blogArticleService.removeById(blogArticle);
            //再删除 标签关系表
            deleteArticleTagRelationship(blogArticle);
            //在删除对应得分类数量 专题数量 用户文章数量
            String blogCategoryName = blogArticle.getBlogCategoryName();
            String blogSpecialId = blogArticle.getBlogSpecialId();
            String userId = blogArticle.getUserId();
            updateBlogCategoryCount(blogCategoryName,-1);
            updateBlogSpecialCount(blogSpecialId,-1);
            updateUserCount(userId,-1);
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

    @GetMapping("/special/{id}")
    public Result getArticle4Special(@PathVariable("id") String id) {
        //找到所有 这个专题的文章
        LambdaQueryWrapper<BlogArticle> blogArticleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        blogArticleLambdaQueryWrapper.eq(BlogArticle::getBlogSpecialId, id);
        return Result.ok(blogArticleService.list(blogArticleLambdaQueryWrapper));
    }


    /**
     * 根据 文章id 删除文章与标签的对应关系
     *
     * @param blogArticle
     * @throws Exception
     */
    private void deleteArticleTagRelationship(BlogArticle blogArticle) throws Exception {
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

    /**
     * 更新 分类 专题数量
     *
     * @param before
     * @param after
     * @throws Exception
     */
    private void updateCount(BlogArticle before, BlogArticle after) throws Exception {
        // TODO 专题
        String beforeBlogSpecialId = before.getBlogSpecialId();
        String afterBlogSpecialId = after.getBlogSpecialId();
        if (beforeBlogSpecialId == null) {
            if (afterBlogSpecialId != null) {
                //之前 等于空 说明 之前没有 现在不等于空 就说明有
                updateBlogSpecialCount(afterBlogSpecialId,1);
            }
        } else {
            //现在的
            if (!Objects.equals(afterBlogSpecialId, beforeBlogSpecialId)) {
                //说明变了
                updateBlogSpecialCount(beforeBlogSpecialId,-1);
                updateBlogSpecialCount(afterBlogSpecialId,1);
            }
        }
        // TODO 分类
        String beforeBlogCategoryName = before.getBlogCategoryName();
        String afterBlogCategoryName = after.getBlogCategoryName();
        if (beforeBlogCategoryName != null) {
            if (!beforeBlogCategoryName.equals(afterBlogCategoryName)) {
                updateBlogCategoryCount(beforeBlogCategoryName,-1);
                updateBlogCategoryCount(afterBlogCategoryName,1);
            }
        }
        if (afterBlogCategoryName != null) {
            updateBlogCategoryCount(afterBlogCategoryName,1);
        }
    }


    private void updateBlogCategoryCount(String blogCategoryName,Integer num) {
        if (blogCategoryName != null) {
            LambdaQueryWrapper<BlogCategory> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(BlogCategory::getBlogCategoryName, blogCategoryName);
            BlogCategory one = blogCategoryService.getOne(wrapper);
            one.setBlogCategoryEssayCount(one.getBlogCategoryEssayCount() + num);
            blogCategoryService.updateById(one);
        }
    }

    private void updateBlogSpecialCount(String blogSpecialId,Integer num) {
        if (blogSpecialId != null) {
            BlogSpecial blogSpecial = blogSpecialService.getById(blogSpecialId);
            blogSpecial.setBlogSpecialEssayCount(blogSpecial.getBlogSpecialEssayCount() + num);
            blogSpecialService.updateById(blogSpecial);
        }
    }

    private void updateUserCount(String userId, Integer num) {
        if (userId != null) {
            User user = userService.getById(userId);
            user.setUserEssayCount(user.getUserEssayCount() + num);
            userService.updateById(user);
        }
    }

}
