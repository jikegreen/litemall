package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.linlinjava.litemall.db.dao.LitemallGoodsAttributeMapper;
import org.linlinjava.litemall.db.domain.LitemallGoodsAttribute;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallGoodsAttributeService extends CommonService<LitemallGoodsAttributeMapper, LitemallGoodsAttribute> {

    public List<LitemallGoodsAttribute> queryByGid(Integer goodsId) {
        return list(new QueryWrapper<LitemallGoodsAttribute>().eq("goods_id", goodsId).eq("deleted", false));
    }

    public void deleteByGid(Integer gid) {
        remove(new UpdateWrapper<LitemallGoodsAttribute>().eq("goods_id", gid));
    }

}
