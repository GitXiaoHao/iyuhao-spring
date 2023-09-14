package top.iyuhao.controller.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.*;
import top.iyuhao.entity.BlogCategory;
import top.iyuhao.entity.BlogStatus;
import top.iyuhao.service.BlogStatusService;
import top.iyuhao.utils.result.Result;

import javax.annotation.Resource;

/**
 * @author yuhao
 * @date 2023/8/28
 **/
@RestController
@RequestMapping("/status")
@Slf4j
public class BlogStatusController {
    @Resource
    private BlogStatusService blogStatusService;
    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    @GetMapping("/list")
    public Result getBlogStatus() {
        return Result.ok(blogStatusService.list());
    }

    @GetMapping("/page/{page}/{pageSize}")
    public Result getStatusByPage(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        if(page == null || page == 0){
            page = 1;
        }
        if(pageSize == null || pageSize == 0){
            pageSize = 5;
        }
        Page<BlogStatus> blogStatusPage = new Page<>(page, pageSize);
        blogStatusService.page(blogStatusPage);
        return Result.ok(blogStatusPage);
    }

    @PostMapping("/add")
    public Result addStatus(@RequestBody BlogStatus blogStatus) {
        return blogStatusService.save(blogStatus) ?
                Result.ok(blogStatus) : Result.fail();
    }

    @PutMapping("/update")
    public Result updateStatus(@RequestBody BlogStatus blogStatus) {
        if (blogStatus.getBlogStatusUpdateTime() != null) {
            blogStatus.setBlogStatusUpdateTime(null);
        }
        return blogStatusService.updateById(blogStatus) ?
                Result.ok(blogStatus) : Result.fail();
    }
    @DeleteMapping("/status")
    public Result delete(@RequestBody BlogStatus blogStatus) {
        return blogStatusService.removeById(blogStatus) ?
                Result.ok("删除成功") : Result.fail("删除失败");
    }


}
