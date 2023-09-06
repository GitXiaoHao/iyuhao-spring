package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 博客文章表
 * @TableName blog_article
 */
@TableName(value ="blog_article")
@Data
public class BlogArticle implements Serializable {
    /**
     * 博客文章id
     */
    @TableId
    private String blogArticleId;

    /**
     * 博客文章标题
     */
    private String blogArticleTitle;

    /**
     * 博客文章分类id
     */
    private String blogCategoryName;

    /**
     * 博客文章封面
     */
    private String blogArticleCover;

    /**
     * 博客文章摘要
     */
    private String blogArticleSummary;

    /**
     * 博客文章内容
     */
    private String blogArticleContent;

    /**
     * 博客文章markdown内容
     */
    private String blogArticleMarkdownContent;

    /**
     * 博客文章编辑器类型
     */
    private String blogArticleEditorType;

    /**
     * 博客文章编辑人id
     */
    private String userId;

    /**
     * 博客文章作者
     */
    private String userName;
    /**
     *文章类型
     */
    private String blogArticleType;
    /**
     *文章转载
     */
    private String blogArticleReprintUrl;

    /**
     * 文章评论权限1:允许0:不允许
     */
    private Integer blogArticleAllowComment;

    /**
     * 博客文章状态
     */
    private String blogStatusName;

    /**
     * 点赞次数
     */
    private Integer blogArticleGoodNumber;

    /**
     * 观看次数
     */
    private Integer blogArticleViewsNumber;

    /**
     * 收藏次数
     */
    private Integer blogArticleFavoriteNumber;

    /**
     * 文章添加时间
     */
    private Date blogArticleAddTime;

    /**
     * 更新时间
     */
    private Date blogArticleUpdateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @TableLogic
    private Integer blogCategoryIsDeleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}