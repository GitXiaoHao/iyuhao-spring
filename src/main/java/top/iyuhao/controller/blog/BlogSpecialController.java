package top.iyuhao.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import top.iyuhao.entity.BlogSpecial;
import top.iyuhao.service.BlogSpecialService;
import top.iyuhao.utils.result.Result;

import javax.annotation.Resource;

/**
 * @author yuhao
 * @date 2023/9/6
 **/
@RestController
@RequestMapping("/blogSpecial")
public class BlogSpecialController {
    @Resource
    private BlogSpecialService blogSpecialService;

    @GetMapping("/special/{page}/{pageSize}")
    public Result<Page<BlogSpecial>> getCategory(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 5;
        }
        Page<BlogSpecial> categoryPage = new Page<>(page, pageSize);
        blogSpecialService.page(categoryPage);
        return Result.ok(categoryPage);
    }


    @DeleteMapping("/special")
    public Result deleteSpecial(@RequestBody BlogSpecial blogSpecial) {
        if (blogSpecial == null) {
            return Result.fail("传输数据失败");
        }
        boolean res = blogSpecialService.removeById(blogSpecial.getBlogSpecialId());
        return res ? Result.ok() : Result.fail("删除失败");
    }


    @PostMapping("/special")
    public Result updateSpecial(@RequestBody BlogSpecial blogSpecial) {
        return blogSpecialService.saveOrUpdate(blogSpecial) ?
                Result.ok(blogSpecial) : Result.fail("修改失败");
    }

    @GetMapping("/list")
    public Result getSpecialList() {
        return Result.ok(blogSpecialService.list());
    }

    @GetMapping("/get/{id}")
    public Result getSpecialById(@PathVariable("id") String id) {
        return Result.ok(blogSpecialService.getById(id));
    }
}
