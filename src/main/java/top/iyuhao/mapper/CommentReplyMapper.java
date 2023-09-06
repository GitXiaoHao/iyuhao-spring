package top.iyuhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.iyuhao.entity.CommentReply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yuhao
* @description 针对表【comment_reply(文章评论回复表)】的数据库操作Mapper
* @createDate 2023-08-20 15:40:28
* @Entity top.iyuhao.entity.CommentReply
*/@Mapper
public interface CommentReplyMapper extends BaseMapper<CommentReply> {

}




