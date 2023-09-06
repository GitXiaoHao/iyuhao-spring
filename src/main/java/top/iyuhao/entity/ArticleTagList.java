package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 文章与文章标签关系表
 * @TableName article_tag_list
 */
@TableName(value ="article_tag_list")
@Data
public class ArticleTagList implements Serializable {
    /**
     * 文章对应标签id
     */
    @TableId
    private String articleTagListId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 文章标签id
     */
    private String articleTagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}