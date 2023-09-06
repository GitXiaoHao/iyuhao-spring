package top.iyuhao.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.iyuhao.utils.mail.MailUtils;
import top.iyuhao.utils.md5.Md5Utils;
import top.iyuhao.utils.result.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author yuhao
 * @date 2023/8/20
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private static final String CHECK_CODE = "checkCode";
    private static final String CHECK_CODE_MAIL = "checkCodeMail";
    @Resource
    private UserService userService;

    /**
     * 获取验证码
     *
     * @return
     * @throws IOException
     */
    @PostMapping("/verify")
    public Result getVerify(HttpSession session, HttpServletResponse response) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 40, 5, 4);
        //获取验证码中的文字内容
        String checkCode = captcha.getCode();
        //存入session
        if (session.getAttribute(CHECK_CODE) != null) {
            session.removeAttribute(CHECK_CODE);
        }
        session.setAttribute(CHECK_CODE, checkCode);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write(response.getOutputStream());
        response.getOutputStream().close();
        return Result.ok(checkCode);
    }

    /**
     * 验证验证码是否正确
     *
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/compareVerify")
    public Result compareVerify(@RequestBody Map<String, Object> map, HttpSession session) {
        String checkCode = session.getAttribute(CHECK_CODE) + "";
        if (!checkCode.equals(map.get(CHECK_CODE))) {
            return Result.fail("验证码错误");
        }
        return Result.ok();
    }

    /**
     * 使用邮箱发送验证码
     *
     * @param map
     * @param session
     * @return
     */
    @GetMapping("/obtainEmail")
    public Result obtainEmail(@RequestBody Map<String, Object> map, HttpSession session) {
        String email = map.get("userEmail").toString();
        if (email == null || "".equals(email)) {
            return Result.fail("邮箱为空");
        }
        String checkCode = RandomUtil.randomNumbers(6);
        session.setAttribute(CHECK_CODE_MAIL, checkCode);
        return MailUtils.sendEmail(email, "注册验证码为：" + checkCode, "iyuhao注册邮件", "2486245007@qq.com")
                ? Result.ok("发送成功")
                : Result.fail("发送失败");
    }

    /**
     * 验证邮箱验证码
     *
     * @param map
     * @param session
     * @return
     */
    @GetMapping("/vcCm")
    public Result verificationCodeMail(@RequestBody Map<String, Object> map, HttpSession session) {
        String code = map.get(CHECK_CODE_MAIL).toString();
        return session.getAttribute(CHECK_CODE_MAIL).toString().equalsIgnoreCase(code)
                ? Result.ok()
                : Result.fail("验证码错误");
    }

    /**
     * 查找验证是否有用户
     *
     * @return
     */
    @GetMapping("/validateUser")
    public Result validateUser(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(user.getUserId() != null, User::getUserId, user.getUserId())
                .eq(user.getUserName() != null, User::getUserName, user.getUserName())
                .eq(user.getUserEmail() != null, User::getUserEmail, user.getUserEmail())
                .eq(user.getUserStatus() != null, User::getUserStatus, user.getUserStatus())
                .eq(user.getUserPassword() != null, User::getUserPassword, Md5Utils.string2Md5(user.getUserPassword()));
        return Result.ok(userService.getOne(wrapper));
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        //md5加密
        user.setUserPassword(Md5Utils.string2Md5(user.getUserPassword()));
        //状态
        user.setUserStatus(1);
        //添加
        return userService.save(user) ? Result.ok(user) : Result.fail();
    }
    @GetMapping("/login")
    public Result login(@RequestBody User user) {
        return this.validateUser(user);
    }

}
