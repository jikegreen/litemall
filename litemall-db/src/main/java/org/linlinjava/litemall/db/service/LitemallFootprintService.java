package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.linlinjava.litemall.db.dao.LitemallFootprintMapper;
import org.linlinjava.litemall.db.domain.LitemallFootprint;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LitemallFootprintService extends CommonService<LitemallFootprintMapper, LitemallFootprint> {

    public List<LitemallFootprint> queryByAddTime(Integer userId, Integer page, Integer size) {
        Page<LitemallFootprint> pa = page(new Page<>(page, size), new QueryWrapper<LitemallFootprint>().eq("user_id", userId).eq("deleted", false).orderBy(Boolean.TRUE, false, "add_time"));
        return pa.getSize()>0? pa.getRecords() : new ArrayList<>();
    }

    public LitemallFootprint findById(Integer userId, Integer id) {
        return getOne(new QueryWrapper<LitemallFootprint>().eq("id", id).eq("user_id", userId).eq("deleted", false));
    }

    public List<LitemallFootprint> querySelective(String userId, String goodsId, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallFootprint> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userId)) {
            wrapper.eq("user_id", Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(goodsId)) {
            wrapper.eq("goods_id", Integer.valueOf(goodsId));
        }
        return paging(wrapper, page, size, sort, order);
    }
}
