package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.CommentReply;
import top.iyuhao.service.CommentReplyService;
import top.iyuhao.mapper.CommentReplyMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【comment_reply(文章评论回复表)】的数据库操作Service实现
* @createDate 2023-08-20 15:40:28
*/
@Service
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply>
    implements CommentReplyService{

}




