package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文章表
 * @TableName article
 */
@TableName(value ="article")
@Data
public class Article implements Serializable {
    /**
     * 文章id
     */
    @TableId
    private String articleId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章内容
     */
    private String articleContext;

    /**
     * 文章添加时间
     */
    private Date articleAddTime;

    /**
     * 更新时间
     */
    private Date articleUpdateTime;

    /**
     * 点赞次数
     */
    private Integer articleGoodNumber;

    /**
     * 观看次数
     */
    private Integer articleViewsNumber;

    /**
     * 收藏次数
     */
    private Integer articleFavoriteNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}