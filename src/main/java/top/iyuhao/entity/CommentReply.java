package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文章评论回复表
 * @TableName comment_reply
 */
@TableName(value ="comment_reply")
@Data
public class CommentReply implements Serializable {
    /**
     * 文章评论回复id
     */
    @TableId
    private String commentReplyId;

    /**
     * 文章评论id
     */
    private String commentId;

    /**
     * 回复人的id
     */
    private String replyUserId;

    /**
     * 回复人的下一个人的id
     */
    private String secondlyUserId;

    /**
     * 文章回复时间
     */
    private Date commentReplyCreateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @TableLogic
    private Integer commentReplyIsDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}