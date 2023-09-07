package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 博客分类类型表
 * @TableName blog_category_type
 */
@TableName(value ="blog_category_type")
@Data
public class BlogCategoryType implements Serializable {
    /**
     * 博客状态id
     */
    @TableId
    private String blogCategoryTypeId;

    /**
     * 博客状态名称
     */
    private String blogCategoryTypeName;

    /**
     * 是否被禁用 1:禁用 0:不禁用
     */
    private Integer blogCategoryTypeDisable;

    /**
     * 创建人id
     */
    private String blogCategoryCreateUserId;

    /**
     * 创建人名称
     */
    private String blogCategoryCreateUserName;

    /**
     * 最后一次修改人id
     */
    private String blogCategoryLastUpdateUserId;

    /**
     * 最后一次修改人名称
     */
    private String blogCategoryLastUpdateUserName;

    /**
     * 文章添加时间
     */
    private Date blogCategoryTypeAddTime;

    /**
     * 更新时间
     */
    private Date blogCategoryTypeUpdateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    private Integer blogCategoryTypeIsDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}