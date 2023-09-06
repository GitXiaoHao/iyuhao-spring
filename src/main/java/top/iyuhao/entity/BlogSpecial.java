package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 博客专题表
 * @TableName blog_special
 */
@TableName(value ="blog_special")
@Data
public class BlogSpecial implements Serializable {
    /**
     * 博客专题id
     */
    @TableId
    private String blogSpecialId;

    /**
     * 博客专题名称
     */
    private String blogSpecialName;

    /**
     * 博客专题封面
     */
    private String blogSpecialCover;

    /**
     * 博客专题简介
     */
    private String blogSpecialBriefIntroduction;

    /**
     * 博客专题编辑人id
     */
    private String userId;

    /**
     * 博客专题作者
     */
    private String userName;

    /**
     * 博客专题最后一次修改人id
     */
    private String blogSpecialLastUpdateUserId;

    /**
     * 点赞次数
     */
    private Integer blogSpecialGoodNumber;

    /**
     * 观看次数
     */
    private Integer blogSpecialViewsNumber;

    /**
     * 收藏次数
     */
    private Integer blogSpecialFavoriteNumber;

    /**
     * 博客专题文章数量
     */
    private Integer blogSpecialEssayCount;

    /**
     * 添加时间
     */
    private Date blogSpecialAddTime;

    /**
     * 更新时间
     */
    private Date blogSpecialUpdateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @TableLogic
    private Integer blogSpecialIsDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}