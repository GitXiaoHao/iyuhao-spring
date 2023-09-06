package top.iyuhao.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.iyuhao.utils.result.Result;

/**
 * @author yuhao
 * @date 2023/8/20
 * 管理后台
 **/
@RestController
@RequestMapping("/yh")
public class AdminController {
    @GetMapping("/")
    public Result adminIndex(){
        return Result.ok();
    }
}
