package top.iyuhao.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import top.iyuhao.entity.BlogCategory;
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
        if(page == null || page == 0){
            page = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 5;
        }
        Page<BlogSpecial> categoryPage = new Page<>(page, pageSize);
        blogSpecialService.page(categoryPage);
        return Result.ok(categoryPage);
    }


    @DeleteMapping("/special")
    public Result deleteSpecial(@RequestBody BlogSpecial blogSpecial){
        if (blogSpecial == null) {
            return Result.fail("传输数据失败");
        }
        boolean res = blogSpecialService.removeById(blogSpecial.getBlogSpecialId());
        return res ? Result.ok() : Result.fail("删除失败");
    }


    @PostMapping("/special")
    public Result updateSpecial(@RequestBody BlogSpecial blogSpecial){
        if (blogSpecial == null) {
            return Result.fail("传输数据失败");
        }
        if (blogSpecial.getBlogSpecialId() != null) {
            //修改
            //   TODO 博客专题最后一次修改人id

            return blogSpecialService.updateById(blogSpecial) ?
                    Result.ok(blogSpecial) : Result.fail("修改失败");

        }
        //添加
        // TODO 博客专题编辑人 博客专题作者

        return blogSpecialService.save(blogSpecial) ?
                Result.ok(blogSpecial) : Result.fail("添加失败");
    }
}
