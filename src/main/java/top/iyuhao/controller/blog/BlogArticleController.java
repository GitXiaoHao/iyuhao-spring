package top.iyuhao.controller.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.iyuhao.dto.BlogArticleDto;
import top.iyuhao.entity.BlogArticle;
import top.iyuhao.service.BlogArticleService;
import top.iyuhao.utils.result.Result;
import top.iyuhao.vo.ArticleSearchDataVo;

import javax.annotation.Resource;

/**
 * @author yuhao
 * @date 2023/8/28
 **/
@RestController
@RequestMapping("/blogArticle")
@Slf4j
public class BlogArticleController {
    @Resource
    private BlogArticleService blogArticleService;

    @PostMapping("/article")
    public Result getArticleByPage(@RequestParam(value = "page",defaultValue = "1") String pageStr, @RequestParam(value = "pageSize",defaultValue = "5") String pageSizeStr, @RequestBody ArticleSearchDataVo articleSearchData){
        int page = Integer.parseInt(pageStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        if(page == 0){
            page = 1;
        }
        if(pageSize == 0){
            pageSize = 5;
        }
        LambdaQueryWrapper<BlogArticle> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(articleSearchData.getTitleFuzzy() != null, BlogArticle::getBlogArticleTitle,articleSearchData.getTitleFuzzy())
                .eq(articleSearchData.getSearchStatus() != null,BlogArticle::getBlogStatusName, articleSearchData.getSearchStatus())
                .eq(articleSearchData.getSearchCategoryName() != null, BlogArticle::getBlogCategoryName,articleSearchData.getSearchCategoryName());
        Page<BlogArticle> articlePage = new Page<>(page, pageSize);
        blogArticleService.page(articlePage,wrapper);
        return Result.ok(articlePage);
    }
    @PostMapping("/addArticle")
    public Result addArticle(@RequestBody BlogArticleDto blogArticleDto){
        for (String tag : blogArticleDto.getTags()) {
            log.info(tag);
        }
        return Result.ok();
    }
}
