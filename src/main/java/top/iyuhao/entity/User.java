package top.iyuhao.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户头像
     */
    private String userCover;

    /**
     * 用户粉丝数量
     */
    private Integer userFans;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 状态
     */
    private Integer userStatus;
    private String useRole;

    /**
     * 用户手机号码
     */
    private String userPhone;

    /**
     * 用户签名
     */
    private String userAutograph;

    /**
     * 创建时间
     */
    private Date userCreateTime;

    /**
     * 更新时间
     */
    private Date userUpdateTime;
    private Date userLastLoginTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    @TableLogic
    private Integer userIsDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}