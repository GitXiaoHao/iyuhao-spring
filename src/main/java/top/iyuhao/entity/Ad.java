package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 广告表
 * @TableName ad
 */
@TableName(value ="ad")
@Data
public class Ad implements Serializable {
    /**
     * 广告id
     */
    @TableId
    private String adId;

    /**
     * 广告类型id
     */
    private String adTypeId;

    /**
     * 广告标题
     */
    private String adTitle;

    /**
     * 广告url
     */
    private String adUrl;

    /**
     * 广告排序
     */
    private Integer adSort;

    /**
     * 广告开始时间
     */
    private Date adBeginTime;

    /**
     * 广告结束时间
     */
    private Date adEndTime;

    /**
     * 添加时间
     */
    private Date adCreateTime;

    /**
     * 修改时间
     */
    private Date adUpdateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @TableLogic
    private Integer adIsDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}