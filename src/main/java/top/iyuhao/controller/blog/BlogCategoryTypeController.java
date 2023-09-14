package top.iyuhao.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import top.iyuhao.entity.BlogCategoryType;
import top.iyuhao.service.BlogCategoryTypeService;
import top.iyuhao.utils.result.Result;

import javax.annotation.Resource;

/**
 * @author yuhao
 * @date 2023/9/7
 **/
@RestController
@RequestMapping("/ct")
public class BlogCategoryTypeController {
    @Resource
    private BlogCategoryTypeService blogCategoryTypeService;

    @GetMapping("/list")
    public Result getBlogCategoryType() {
        return Result.ok(blogCategoryTypeService.list());
    }

    @GetMapping("/{page}/{pageSize}")
    public Result getCategoryTypeByPage(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 5;
        }
        Page<BlogCategoryType> blogCategoryTypePage = new Page<>(page, pageSize);
        blogCategoryTypeService.page(blogCategoryTypePage);
        return Result.ok(blogCategoryTypePage);
    }

    @PostMapping("/add")
    public Result addCategoryType(@RequestBody BlogCategoryType blogCategoryType) {
        return blogCategoryTypeService.save(blogCategoryType) ?
                Result.ok(blogCategoryType) : Result.fail();
    }

    @PutMapping("/update")
    public Result updateCategoryType(@RequestBody BlogCategoryType blogCategoryType) {
        if (blogCategoryType.getBlogCategoryTypeUpdateTime() != null) {
            blogCategoryType.setBlogCategoryTypeUpdateTime(null);
        }
        return blogCategoryTypeService.updateById(blogCategoryType) ?
                Result.ok(blogCategoryType) : Result.fail();
    }

    @DeleteMapping("/")
    public Result deleteCategoryType(@RequestBody BlogCategoryType blogCategoryType) {
        return blogCategoryTypeService.removeById(blogCategoryType) ?
                Result.ok("删除成功") : Result.fail("删除失败");
    }



}
