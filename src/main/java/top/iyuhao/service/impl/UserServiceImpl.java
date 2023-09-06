package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.User;
import top.iyuhao.service.UserService;
import top.iyuhao.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2023-09-06 16:48:45
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




