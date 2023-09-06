package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文章标签表
 * @TableName article_tag
 */
@TableName(value ="article_tag")
@Data
public class ArticleTag implements Serializable {
    /**
     * 文章标签id
     */
    @TableId
    private String articleTagId;

    /**
     * 标签名称
     */
    private String articleTagName;

    /**
     * 文章标签添加时间
     */
    private Date articleTagAddTime;

    /**
     * 文章标签更新时间
     */
    private Date articleTagUpdateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @TableLogic
    private Integer articleTagIsDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}