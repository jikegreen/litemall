package org.linlinjava.litemall.db.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 优惠券信息及规则表
 * </p>
 *
 * @author 作者
 * @since 2022-02-25
 */
@TableName(value = "litemall_coupon", autoResultMap = true)
public class LitemallCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券介绍，通常是显示优惠券使用限制文字
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 优惠券标签，例如新人专用
     */
    private String tag;

    /**
     * 优惠券数量，如果是0，则是无限量
     */
    private Integer total;

    /**
     * 优惠金额，
     */
    private BigDecimal discount;

    /**
     * 最少消费金额才能使用优惠券。
     */
    private BigDecimal min;

    /**
     * 用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.
     */
    @TableField("`limit`")
    private Integer limit;

    /**
     * 优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；
     */
    private Integer type;

    /**
     * 优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。
     */
    private Integer status;

    /**
     * 商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
     */
    private Integer goodsType;

    /**
     * 商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> goodsValue;

    /**
     * 优惠券兑换码
     */
    private String code;

    /**
     * 有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；
     */
    private Integer timeType;

    /**
     * 基于领取时间的有效天数days。
     */
    private Integer days;

    /**
     * 使用券开始时间
     */
    private LocalDateTime startTime;

    /**
     * 使用券截至时间
     */
    private LocalDateTime endTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public List<String> getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(List<String> goodsValue) {
        this.goodsValue = goodsValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
        return "LitemallCoupon{" +
        "id=" + id +
        ", name=" + name +
        ", desc=" + desc +
        ", tag=" + tag +
        ", total=" + total +
        ", discount=" + discount +
        ", min=" + min +
        ", limit=" + limit +
        ", type=" + type +
        ", status=" + status +
        ", goodsType=" + goodsType +
        ", goodsValue=" + goodsValue +
        ", code=" + code +
        ", timeType=" + timeType +
        ", days=" + days +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", addTime=" + addTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        "}";
    }
}
