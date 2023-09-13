package top.iyuhao.controller.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.iyuhao.entity.User;
import top.iyuhao.service.UserService;
import top.iyuhao.token.TokenUtils;
import top.iyuhao.utils.md5.Md5Utils;
import top.iyuhao.utils.result.Result;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuhao
 * @date 2023/9/6
 **/
@RestController
@RequestMapping("/blogAdmin")
@Slf4j
public class BlogAdminController {
    @Resource
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(user.getUserId() != null, User::getUserId, user.getUserId())
                .eq(user.getUserName() != null, User::getUserName, user.getUserName())
                .eq(user.getUserPassword() != null, User::getUserPassword, Md5Utils.string2Md5(user.getUserPassword()));
        user = userService.getOne(wrapper);
        if (user == null) {
            return Result.fail("密码错误");
        }
        String token = TokenUtils.sign(user.getUserName(), user.getUserPassword());
        Map<String,Object> map = new HashMap<>(2);
        map.put("accessToken",token);
        map.put("adminInfo",user);
        return Result.ok(map);
    }


    @PostMapping("/register")
    public Result register(){
        return Result.ok();
    }

    @PutMapping("/admin")
    public Result saveAdmin(@RequestBody User user){
        return userService.updateById(user) ?
                Result.ok(user) : Result.fail("保存失败");
    }
    @GetMapping("/list")
    public Result getAdminByList(){
        return Result.ok(userService.list());
    }
}
