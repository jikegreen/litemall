package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.linlinjava.litemall.db.dao.LitemallCartMapper;
import org.linlinjava.litemall.db.domain.LitemallCart;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallCartService extends CommonService<LitemallCartMapper, LitemallCart> {

    public LitemallCart queryExist(Integer goodsId, Integer productId, Integer userId) {
        return getOne(new QueryWrapper<LitemallCart>().eq("goods_id", goodsId).eq("product_id", productId).eq("user_id", userId).eq("deleted", false));
    }

    public List<LitemallCart> queryByUid(int userId) {
        return list(new QueryWrapper<LitemallCart>().eq("user_id", userId).eq("deleted", false));
    }

    public List<LitemallCart> queryByUidAndChecked(Integer userId) {
        return list(new QueryWrapper<LitemallCart>().eq("checked", true).eq("user_id", userId).eq("deleted", false));
    }

    public boolean delete(List<Integer> productIdList, int userId) {
        return remove(new QueryWrapper<LitemallCart>().eq("user_id", userId).in("product_id", productIdList));
    }

    public LitemallCart findById(Integer userId, Integer id) {
        return getOne(new QueryWrapper<LitemallCart>().eq("id", id).eq("user_id", userId).eq("deleted", false));
    }

    public boolean updateCheck(Integer userId, List<Integer> idsList, Boolean checked) {
        return update(new UpdateWrapper<LitemallCart>().set("checked", true).set("update_time", LocalDateTime.now()).eq("user_id", userId).in("product_id", idsList).eq("deleted", false));
    }

    public void clearGoods(Integer userId) {
        update(new UpdateWrapper<LitemallCart>().set("deleted", true).eq("user_id", userId).eq("checked", true));
    }

    public List<LitemallCart> querySelective(Integer userId, Integer goodsId, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallCart> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userId)) {
            wrapper.eq("user_id", userId);
        }
        if (!StringUtils.isEmpty(goodsId)) {
            wrapper.eq("goods_id", goodsId);
        }
        return paging(wrapper, page, limit, sort, order);
    }

    public void updateProduct(Integer id, String goodsSn, String goodsName, BigDecimal price, String url) {
        update(new UpdateWrapper<LitemallCart>().set("price", price).set("pic_url",url).set("goods_sn", goodsSn).set("goods_name", goodsName).eq("product_id", id));
    }
}
