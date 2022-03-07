package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.RequiredArgsConstructor;
import org.linlinjava.litemall.db.dao.GoodsProductMapper;
import org.linlinjava.litemall.db.dao.LitemallGoodsProductMapper;
import org.linlinjava.litemall.db.domain.LitemallGoodsProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LitemallGoodsProductService extends CommonService<LitemallGoodsProductMapper, LitemallGoodsProduct> {
    private final GoodsProductMapper goodsProductMapper;

    public List<LitemallGoodsProduct> queryByGid(Integer gid) {
        return list(new QueryWrapper<LitemallGoodsProduct>().eq("goods_id", gid).eq("deleted", false));
    }

    public void deleteByGid(Integer gid) {
        remove(new UpdateWrapper<LitemallGoodsProduct>().eq("goods_id", gid));
    }

    public int addStock(Integer id, Integer num){
        return goodsProductMapper.addStock(id, num);
    }

    public int reduceStock(Integer id, Integer num){
        return goodsProductMapper.reduceStock(id, num);
    }

}
