package top.iyuhao.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.*;
import top.iyuhao.entity.BlogCategory;
import top.iyuhao.service.BlogCategoryService;
import top.iyuhao.utils.result.Result;

import javax.annotation.Resource;

/**
 * @author yuhao
 * @date 2023/8/25
 **/
@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogCategoryController {
    @Resource
    private BlogCategoryService blogCategoryService;
    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    @GetMapping("/category/{page}/{pageSize}")
    public Result<Page<BlogCategory>> getCategory(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize) {
        if(page == null || page == 0){
            page = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 5;
        }
        Page<BlogCategory> categoryPage = new Page<>(page, pageSize);
        blogCategoryService.page(categoryPage);
        return Result.ok(categoryPage);
    }

    @GetMapping("/categoryList")
    public Result getCategoryList() {
        return Result.ok(blogCategoryService.getAll());
    }

    @PostMapping("/category")
    public Result addCategory(@RequestBody BlogCategory category) {
        //开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        boolean save = false;
        try{
            //先根据id删除 再新增
            if (category.getBlogCategoryId() != null) {
                blogCategoryService.removeById(category.getBlogCategoryId());
            }
            //再新增
            save = blogCategoryService.save(category);
            dataSourceTransactionManager.commit(transactionStatus);
        }catch (Exception e){
            dataSourceTransactionManager.rollback(transactionStatus);
        }
        return save ? Result.ok("添加成功") : Result.fail("添加失败");
    }
    @DeleteMapping("/category")
    public Result deleteCategory(@RequestBody BlogCategory category){
        boolean del = false;
        if (category.getBlogCategoryId() != null) {
            del = blogCategoryService.removeById(category.getBlogCategoryId());
        }
        return del ? Result.ok("删除成功") : Result.fail("删除失败");
    }
}
