package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.linlinjava.litemall.db.dao.LitemallOrderGoodsMapper;
import org.linlinjava.litemall.db.domain.LitemallOrderGoods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallOrderGoodsService extends CommonService<LitemallOrderGoodsMapper, LitemallOrderGoods> {
    public List<LitemallOrderGoods> queryByOid(Integer orderId) {
        return list(new QueryWrapper<LitemallOrderGoods>().eq("order_id", orderId).eq("deleted", false));
    }

    public List<LitemallOrderGoods> findByOidAndGid(Integer orderId, Integer goodsId) {
        return list(new QueryWrapper<LitemallOrderGoods>().eq("order_id", orderId).eq("goods_id", goodsId).eq("deleted", false));
    }

    public Integer getComments(Integer orderId) {
        return (int) count(new QueryWrapper<LitemallOrderGoods>().eq("order_id", orderId).eq("deleted", false));
    }

    public boolean checkExist(Integer goodsId) {
        return count(new QueryWrapper<LitemallOrderGoods>().eq("goods_id", goodsId).eq("deleted", false))>0;
    }

    public void deleteByOrderId(Integer orderId) {
        remove(new UpdateWrapper<LitemallOrderGoods>().eq("order_id", orderId).eq("deleted", false));
    }
}
