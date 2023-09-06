package top.iyuhao.controller.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.iyuhao.service.BlogStatusService;
import top.iyuhao.utils.result.Result;

import javax.annotation.Resource;

/**
 * @author yuhao
 * @date 2023/8/28
 **/
@RestController
@RequestMapping("/blogStatus")
@Slf4j
public class BlogStatusController {
    @Resource
    private BlogStatusService blogStatusService;

    @GetMapping("/list")
    public Result getBlogStatus() {
        return Result.ok(blogStatusService.list());
    }
}
