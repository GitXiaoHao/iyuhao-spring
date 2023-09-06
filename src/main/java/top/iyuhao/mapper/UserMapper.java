package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【user(用户信息表)】的数据库操作Mapper
* @createDate 2023-09-06 16:48:45
* @Entity top.iyuhao.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




