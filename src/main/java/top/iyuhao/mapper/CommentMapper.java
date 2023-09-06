package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【comment(文章评论表)】的数据库操作Mapper
* @createDate 2023-08-20 15:40:25
* @Entity top.iyuhao.entity.Comment
*/@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




