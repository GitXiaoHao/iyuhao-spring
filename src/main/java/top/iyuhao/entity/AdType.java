package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 广告类型表
 * @TableName ad_type
 */
@TableName(value ="ad_type")
@Data
public class AdType implements Serializable {
    /**
     * 广告类型id
     */
    @TableId
    private String adTypeId;

    /**
     * 广告类型名称
     */
    private String adTypeName;

    /**
     * 广告标示
     */
    private String adTypeTag;

    /**
     * 广告类型排序
     */
    private Integer adTypeSort;

    /**
     * 创建时间
     */
    private Date adTypeCreateTime;

    /**
     * 更新时间
     */
    private Date adTypeUpdateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @TableLogic
    private Integer adTypeIsDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}