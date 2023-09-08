package top.iyuhao.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.*;
import top.iyuhao.dto.ArticleTagDto;
import top.iyuhao.entity.ArticleTag;
import top.iyuhao.service.ArticleTagService;
import top.iyuhao.utils.result.Result;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yuhao
 * @date 2023/9/8
 **/
@RestController
@RequestMapping("/at")
@Slf4j
public class ArticleTagController {
    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;
    @Resource
    private ArticleTagService articleTagService;

    @GetMapping("/page/{page}/{pageSize}")
    public Result getArticleTagByPage(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 5;
        }
        Page<ArticleTag> articleTagPage = new Page<>(page, pageSize);
        articleTagService.page(articleTagPage);
        return Result.ok(articleTagPage);
    }

    @GetMapping("/list")
    public Result getArticleTagList() {
        List<ArticleTag> list = articleTagService.list();
        return Result.ok(list);
    }

    @PostMapping("/add")
    public Result addArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.save(articleTag) ?
                Result.ok(articleTag) : Result.fail();
    }

    @PutMapping("/update")
    public Result updateArticleTag(@RequestBody ArticleTag articleTag) {
        return articleTagService.updateById(articleTag) ?
                Result.ok(articleTag) : Result.fail();
    }

    @DeleteMapping("/")
    public Result delete(@RequestBody ArticleTag articleTag) {
        //开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        boolean res = false;
        try {
            //需要查找子节点
            List<ArticleTag> articleTagList = articleTagService.list();
            List<ArticleTag> updateList = new ArrayList<>();
            for (ArticleTag tag : articleTagList) {
                if (Objects.equals(tag.getArticleTagParentId(), articleTag.getArticleTagId())) {
                    //相等 则滞空
                    tag.setArticleTagParentId(null);
                    tag.setArticleTagParentName(null);
                    updateList.add(tag);
                }
            }
            //删除
            articleTagService.removeById(articleTag);
            //修改
            articleTagService.updateBatchById(updateList);
            dataSourceTransactionManager.commit(transactionStatus);
            res = true;
        } catch (Exception e) {
            log.error(e.getMessage());
            dataSourceTransactionManager.rollback(transactionStatus);
        }
        return res ? Result.ok("删除成功") : Result.fail("删除失败");
    }

    @GetMapping("/tree")
    public Result getArticleTagByTree() {
        //找出所有
        List<ArticleTag> articleTagList = articleTagService.list();
        List<ArticleTagDto> articleTagDtoList = new ArrayList<>();
        //查看是否有父级
        for (ArticleTag articleTag : articleTagList) {
            if (articleTag.getArticleTagParentId() == null) {
                //如果没有父级则直接加入
                articleTagDtoList.add(new ArticleTagDto(articleTag, new ArrayList<>()));
            }
        }
        for (ArticleTag articleTag : articleTagList) {
            if (articleTag.getArticleTagParentId() != null) {
                for (ArticleTagDto articleTagDto : articleTagDtoList) {
                    getChild(articleTag, articleTagDto);
                }
            }
        }
        return Result.ok(articleTagDtoList);
    }

    private boolean getChild(ArticleTag articleTag, ArticleTagDto articleTagDto) {
        if (Objects.equals(articleTag.getArticleTagParentId(), articleTagDto.getArticleTag().getArticleTagId())) {
            articleTagDto.getChildren().add(new ArticleTagDto(articleTag, new ArrayList<>()));
            return true;
        }
        //如果不相等
        if (articleTagDto.getChildren().size() != 0) {
            for (ArticleTagDto child : articleTagDto.getChildren()) {
                return getChild(articleTag, child);
            }
        }
        return false;
    }
}
