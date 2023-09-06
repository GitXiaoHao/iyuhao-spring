package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 链接表
 * @TableName link
 */
@TableName(value ="link")
@Data
public class Link implements Serializable {
    /**
     * 链接id
     */
    @TableId
    private String linkId;

    /**
     * 链接标题
     */
    private String linkName;

    /**
     * 链接地址
     */
    private String linkUrl;

    /**
     * 链接logo
     */
    private String linkLogoUrl;

    /**
     * 添加时间
     */
    private Date linkCreateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}