package org.linlinjava.litemall.db.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 售后表
 * </p>
 *
 * @author 作者
 * @since 2022-02-25
 */
@TableName("litemall_aftersale")
public class LitemallAftersale implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 售后编号
     */
    private String aftersaleSn;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 售后类型，0是未收货退款，1是已收货（无需退货）退款，2用户退货退款
     */
    private Integer type;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 退款金额
     */
    private BigDecimal amount;

    /**
     * 退款凭证图片链接数组
     */
    private String pictures;

    /**
     * 退款说明
     */
    private String comment;

    /**
     * 售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消
     */
    private Integer status;

    /**
     * 管理员操作时间
     */
    private LocalDateTime handleTime;

    /**
     * 添加时间
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

    public String getAftersaleSn() {
        return aftersaleSn;
    }

    public void setAftersaleSn(String aftersaleSn) {
        this.aftersaleSn = aftersaleSn;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(LocalDateTime handleTime) {
        this.handleTime = handleTime;
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
        return "LitemallAftersale{" +
        "id=" + id +
        ", aftersaleSn=" + aftersaleSn +
        ", orderId=" + orderId +
        ", userId=" + userId +
        ", type=" + type +
        ", reason=" + reason +
        ", amount=" + amount +
        ", pictures=" + pictures +
        ", comment=" + comment +
        ", status=" + status +
        ", handleTime=" + handleTime +
        ", addTime=" + addTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        "}";
    }
}
