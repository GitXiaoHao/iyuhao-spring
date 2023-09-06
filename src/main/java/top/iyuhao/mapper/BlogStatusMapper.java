package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.BlogStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【blog_status(博客状态表)】的数据库操作Mapper
* @createDate 2023-08-28 18:00:36
* @Entity top.iyuhao.entity.BlogStatus
*/
@Mapper
public interface BlogStatusMapper extends BaseMapper<BlogStatus> {

}




