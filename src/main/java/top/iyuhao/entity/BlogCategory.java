package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 博客分类表
 * @TableName blog_category
 */
@TableName(value ="blog_category")
@Data
public class BlogCategory implements Serializable {
    /**
     * 分类id
     */
    @TableId
    private String blogCategoryId;

    /**
     * 分类图片
     */
    private String blogCategoryCover;

    /**
     * 分类名称
     */
    private String blogCategoryName;

    /**
     * 分类类型
     */
    private String blogCategoryType;
    /**
     * 分类简介
     */
    private String blogCategoryDesc;

    /**
     * 创建分类用户id
     */
    private String userId;

    /**
     * 分类文章数量
     */
    private Integer blogCategoryEssayCount;

    /**
     * 创建时间
     */
    private Date blogCategoryCreateTime;
    private Date blogCategoryUpdateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @TableLogic
    private Integer blogCategoryIsDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}