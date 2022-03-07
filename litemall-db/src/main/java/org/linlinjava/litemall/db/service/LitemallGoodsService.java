package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.linlinjava.litemall.db.dao.LitemallGoodsMapper;
import org.linlinjava.litemall.db.domain.LitemallGoods;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LitemallGoodsService extends CommonService<LitemallGoodsMapper, LitemallGoods> {
    String[] columns = {"id", "name", "brief", "pic_url", "is_hot", "is_new", "counter_price", "retail_price"};

    /**
     * 获取热卖商品
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallGoods> queryByHot(int offset, int limit) {
        QueryWrapper<LitemallGoods> wrapper = new QueryWrapper<>();
        wrapper.select(columns).eq("is_hot", true).eq("is_on_sale", true);
        return paging(wrapper, offset, limit, null, null, Boolean.TRUE);
    }

    public Page<LitemallGoods> pageByHot(int offset, int limit) {
        QueryWrapper<LitemallGoods> wrapper = new QueryWrapper<>();
        wrapper.select(columns).eq("is_hot", true).eq("is_on_sale", true).eq("deleted", false).orderBy(Boolean.TRUE, false, "add_time");
        return page(new Page<>(offset, limit), wrapper);
    }

    /**
     * 获取新品上市
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallGoods> queryByNew(int offset, int limit) {
        QueryWrapper<LitemallGoods> wrapper = new QueryWrapper<>();
        wrapper.select(columns).eq("is_new", true).eq("is_on_sale", true);
        return paging(wrapper, offset, limit, null, null, Boolean.TRUE);
    }

    public Page<LitemallGoods> pageByNew(int offset, int limit) {
        QueryWrapper<LitemallGoods> wrapper = new QueryWrapper<>();
        wrapper.select(columns).eq("is_new", true).eq("is_on_sale", true).eq("deleted", false).orderBy(Boolean.TRUE, false, "add_time");;
        return page(new Page<>(offset, limit), wrapper);
    }

    /**
     * 获取分类下的商品
     *
     * @param catList
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallGoods> queryByCategory(List<Integer> catList, int offset, int limit) {
        QueryWrapper<LitemallGoods> wrapper = new QueryWrapper<>();
        wrapper.select(columns).in("category_id", catList).eq("is_on_sale", true);
        return paging(wrapper, offset, limit, null, null, Boolean.TRUE);
    }


    /**
     * 获取分类下的商品
     *
     * @param catId
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallGoods> queryByCategory(Integer catId, int offset, int limit) {
        QueryWrapper<LitemallGoods> wrapper = new QueryWrapper<>();
        wrapper.select(columns).eq("category_id", catId).eq("is_on_sale", true);
        return paging(wrapper, offset, limit, null, null, Boolean.TRUE);
    }


    public List<LitemallGoods> querySelective(Integer catId, Integer brandId, String keywords, Boolean isHot, Boolean isNew, Integer offset, Integer limit, String sort, String order) {
        QueryWrapper<LitemallGoods> wrapper = new QueryWrapper<>();
        wrapper.select(columns);
        if (!StringUtils.isEmpty(catId) && catId != 0) {
            wrapper.eq("category_id", catId);
        }
        if (!StringUtils.isEmpty(brandId)) {
            wrapper.eq("brand_id", brandId);
        }
        if (!StringUtils.isEmpty(isNew)) {
            wrapper.eq("is_new", isNew);
        }
        if (!StringUtils.isEmpty(isHot)) {
            wrapper.eq("is_hot", isHot);
        }
        if (!StringUtils.isEmpty(keywords)) {
            wrapper.like("keywords", keywords).or().like("name", keywords);
        }
        wrapper.eq("is_on_sale", true);
        return paging(wrapper, offset, limit, sort, order);
    }

    public Page<LitemallGoods> query(Integer catId, Integer brandId, String keywords, Boolean isHot, Boolean isNew, Integer offset, Integer limit, String sort, String order) {
        QueryWrapper<LitemallGoods> wrapper = new QueryWrapper<>();
        wrapper.select(columns);
        if (!StringUtils.isEmpty(catId) && catId != 0) {
            wrapper.eq("category_id", catId);
        }
        if (!StringUtils.isEmpty(brandId)) {
            wrapper.eq("brand_id", brandId);
        }
        if (!StringUtils.isEmpty(isNew)) {
            wrapper.eq("is_new", isNew);
        }
        if (!StringUtils.isEmpty(isHot)) {
            wrapper.eq("is_hot", isHot);
        }
        if (!StringUtils.isEmpty(keywords)) {
            wrapper.like("keywords", keywords).or().like("name", keywords);
        }
        wrapper.eq("is_on_sale", true).eq("deleted", false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            wrapper.orderBy(Boolean.TRUE, "asc".equals(order), sort);
        }
        Page<LitemallGoods> pa = page(new Page<>(offset, limit), wrapper);
        return pa;
    }

    public List<LitemallGoods> querySelective(Integer goodsId, String goodsSn, String name, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallGoods> wrapper = new QueryWrapper<>();
        if (goodsId != null) {
            wrapper.eq("id", goodsId);
        }
        if (!StringUtils.isEmpty(goodsSn)) {
            wrapper.eq("goods_sn", goodsSn);
        }
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        return paging(wrapper, page, size, sort, order);
    }

    /**
     * 获取某个商品信息,包含完整信息
     *
     * @param id
     * @return
     */
    public LitemallGoods findById(Integer id) {
        return findById(id, Boolean.FALSE);
    }

    /**
     * 获取某个商品信息，仅展示相关内容
     *
     * @param id
     * @return
     */
    public LitemallGoods findByIdVO(Integer id) {
        return getOne(new QueryWrapper<LitemallGoods>().select(columns).eq("id", id).eq("is_on_sale", true).eq("deleted", false));
    }


    /**
     * 获取所有在售物品总数
     *
     * @return
     */
    public Integer queryOnSale() {
        return (int) count(new QueryWrapper<LitemallGoods>().eq("is_on_sale", true).eq("deleted", false));
    }

    public List<Integer> getCatIds(Integer brandId, String keywords, Boolean isHot, Boolean isNew) {
        QueryWrapper<LitemallGoods> wrapper = new QueryWrapper<>();
        wrapper.select("category_id");
        if (!StringUtils.isEmpty(brandId)) {
            wrapper.eq("brand_id", brandId);
        }
        if (!StringUtils.isEmpty(isNew)) {
            wrapper.eq("is_new", isNew);
        }
        if (!StringUtils.isEmpty(isHot)) {
            wrapper.eq("is_hot", isHot);
        }
        if (!StringUtils.isEmpty(keywords)) {
            wrapper.like("keywords", keywords).or().like("name", keywords);
        }
        wrapper.eq("is_on_sale", true).eq("deleted", false);
        List<LitemallGoods> goodsList = list(wrapper);
        List<Integer> cats = new ArrayList<Integer>();
        for (LitemallGoods goods : goodsList) {
            cats.add(goods.getCategoryId());
        }
        return cats;
    }

    public boolean checkExistByName(String name) {
        return count(new QueryWrapper<LitemallGoods>().eq("name", name).eq("is_on_sale", true).eq("deleted", false))>0;
    }

    public List<LitemallGoods> queryByIds(List<Integer> ids) {
        return list(new QueryWrapper<LitemallGoods>().select(columns).in("id", ids).eq("is_on_sale", true).eq("deleted", false));
    }
}
