package org.linlinjava.litemall.db.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 团购活动表
 * </p>
 *
 * @author 作者
 * @since 2022-02-25
 */
@TableName("litemall_groupon")
public class LitemallGroupon implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联的订单ID
     */
    private Integer orderId;

    /**
     * 如果是开团用户，则groupon_id是0；如果是参团用户，则groupon_id是团购活动ID
     */
    private Integer grouponId;

    /**
     * 团购规则ID，关联litemall_groupon_rules表ID字段
     */
    private Integer rulesId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 团购分享图片地址
     */
    private String shareUrl;

    /**
     * 开团用户ID
     */
    private Integer creatorUserId;

    /**
     * 开团时间
     */
    private LocalDateTime creatorUserTime;

    /**
     * 团购活动状态，开团未支付则0，开团中则1，开团失败则2
     */
    private Integer status;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGrouponId() {
        return grouponId;
    }

    public void setGrouponId(Integer grouponId) {
        this.grouponId = grouponId;
    }

    public Integer getRulesId() {
        return rulesId;
    }

    public void setRulesId(Integer rulesId) {
        this.rulesId = rulesId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public LocalDateTime getCreatorUserTime() {
        return creatorUserTime;
    }

    public void setCreatorUserTime(LocalDateTime creatorUserTime) {
        this.creatorUserTime = creatorUserTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "LitemallGroupon{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", grouponId=" + grouponId +
        ", rulesId=" + rulesId +
        ", userId=" + userId +
        ", shareUrl=" + shareUrl +
        ", creatorUserId=" + creatorUserId +
        ", creatorUserTime=" + creatorUserTime +
        ", status=" + status +
        ", addTime=" + addTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        "}";
    }
}
