package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 博客状态表
 * @TableName blog_status
 */
@TableName(value ="blog_status")
@Data
public class BlogStatus implements Serializable {
    /**
     * 博客状态id
     */
    @TableId
    private String blogStatusId;

    /**
     * 博客状态名称
     */
    private String blogStatusName;

    /**
     * 博客状态颜色
     */
    private String blogStatusColor;

    /**
     * 文章添加时间
     */
    private Date blogStatusAddTime;

    /**
     * 更新时间
     */
    private Date blogStatusUpdateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @TableLogic
    private Integer blogStatusIsDeleted;
    private String blogStatusDisable;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}