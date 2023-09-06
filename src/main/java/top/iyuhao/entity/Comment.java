package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文章评论表
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 文章评论id
     */
    @TableId
    private String commentId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 文章评论用户id
     */
    private String userId;

    /**
     * 文章评论时间
     */
    private Date commentTime;

    /**
     * 点赞次数
     */
    private Integer commentGoodNumber;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @TableLogic
    private Integer commentIsDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}