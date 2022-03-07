package org.linlinjava.litemall.db.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 商品基本信息表
 * </p>
 *
 * @author 作者
 * @since 2022-02-25
 */
@TableName(value = "litemall_goods", autoResultMap = true)
public class LitemallGoods implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品所属类目ID
     */
    private Integer categoryId;

    private Integer brandId;

    /**
     * 商品宣传图片列表，采用JSON数组格式
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> gallery;

    /**
     * 商品关键字，采用逗号间隔
     */
    private String keywords;

    /**
     * 商品简介
     */
    private String brief;

    /**
     * 是否上架
     */
    private Boolean isOnSale;

    private Integer sortOrder;

    /**
     * 商品页面商品图片
     */
    private String picUrl;

    /**
     * 商品分享海报
     */
    private String shareUrl;

    /**
     * 是否新品首发，如果设置则可以在新品首发页面展示
     */
    private Boolean isNew;

    /**
     * 是否人气推荐，如果设置则可以在人气推荐页面展示
     */
    private Boolean isHot;

    /**
     * 商品单位，例如件、盒
     */
    private String unit;

    /**
     * 专柜价格
     */
    private BigDecimal counterPrice;

    /**
     * 零售价格
     */
    private BigDecimal retailPrice;

    /**
     * 商品详细介绍，是富文本格式
     */
    private String detail;

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

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Boolean getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getCounterPrice() {
        return counterPrice;
    }

    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
        return "LitemallGoods{" +
        "id=" + id +
        ", goodsSn=" + goodsSn +
        ", name=" + name +
        ", categoryId=" + categoryId +
        ", brandId=" + brandId +
        ", gallery=" + gallery +
        ", keywords=" + keywords +
        ", brief=" + brief +
        ", isOnSale=" + isOnSale +
        ", sortOrder=" + sortOrder +
        ", picUrl=" + picUrl +
        ", shareUrl=" + shareUrl +
        ", isNew=" + isNew +
        ", isHot=" + isHot +
        ", unit=" + unit +
        ", counterPrice=" + counterPrice +
        ", retailPrice=" + retailPrice +
        ", detail=" + detail +
        ", addTime=" + addTime +
        ", updateTime=" + updateTime +
        ", deleted=" + deleted +
        "}";
    }
}
