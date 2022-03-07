package org.linlinjava.litemall.db.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author 作者
 * @since 2022-02-25
 */
@TableName(value = "litemall_admin", autoResultMap = true)
public class LitemallAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员名称
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 最近一次登录IP地址
     */
    private String lastLoginIp;

    /**
     * 最近一次登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 头像图片
     */
    private String avatar;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 角色列表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Integer[] roleIds;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "LitemallAdmin{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", lastLoginIp=" + lastLoginIp +
        ", lastLoginTime=" + lastLoginTime +
        ", avatar=" + avatar +
        ", addTime=" + addTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        ", roleIds=" + roleIds +
        "}";
    }
}
