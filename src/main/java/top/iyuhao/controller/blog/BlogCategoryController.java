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
@RequestMapping("/category")
@Slf4j
public class BlogCategoryController {
    @Resource
    private BlogCategoryService blogCategoryService;
    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    @GetMapping("/{page}/{pageSize}")
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

    @GetMapping("/list")
    public Result getCategoryList() {
        return Result.ok(blogCategoryService.getAll());
    }
    @GetMapping("/get/{id}")
    public Result getCategoryById(@PathVariable("id") String id){
        return Result.ok(blogCategoryService.getById(id));
    }
    @PostMapping("/add")
    public Result addCategory(@RequestBody BlogCategory category) {
        return blogCategoryService.save(category) ? Result.ok("添加成功") : Result.fail("添加失败");
    }

    @PutMapping("/update")
    public Result updateCategory(@RequestBody BlogCategory category) {
        return blogCategoryService.updateById(category) ? Result.ok("添加成功") : Result.fail("添加失败");
    }

    @DeleteMapping
    public Result deleteCategory(@RequestBody BlogCategory category){
        boolean del = false;
        if (category.getBlogCategoryId() != null) {
            del = blogCategoryService.removeById(category.getBlogCategoryId());
        }
        return del ? Result.ok("删除成功") : Result.fail("删除失败");
    }
}
