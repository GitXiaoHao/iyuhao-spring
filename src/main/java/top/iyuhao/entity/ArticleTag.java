package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
     * 创建人id
     */
    private String articleTagCreateUserId;

    /**
     * 创建人名称
     */
    private String articleTagCreateUserName;

    /**
     * 最后一次修改人id
     */
    private String articleTagLastUpdateUserId;

    /**
     * 最后一次修改人名称
     */
    private String articleTagLastUpdateUserName;

    /**
     * 标签上一级的id
     */
    private String articleTagParentId;

    /**
     * 标签父级名称
     */
    private String articleTagParentName;

    /**
     * 标签父级名称
     */
    private Integer articleTagDisable;

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
    private Integer articleTagIsDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}