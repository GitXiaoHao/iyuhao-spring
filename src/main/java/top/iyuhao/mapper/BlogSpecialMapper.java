package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.BlogSpecial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【blog_special(博客专题表)】的数据库操作Mapper
* @createDate 2023-09-05 21:53:18
* @Entity top.iyuhao.entity.BlogSpecial
*/
@Mapper
public interface BlogSpecialMapper extends BaseMapper<BlogSpecial> {

}




