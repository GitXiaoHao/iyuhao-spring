package top.iyuhao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.iyuhao.entity.Comment;
import top.iyuhao.service.CommentService;
import top.iyuhao.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author yuhao
* @description 针对表【comment(文章评论表)】的数据库操作Service实现
* @createDate 2023-08-20 15:40:25
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




