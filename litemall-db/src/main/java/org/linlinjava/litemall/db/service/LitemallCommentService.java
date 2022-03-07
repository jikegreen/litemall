package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.linlinjava.litemall.db.dao.LitemallCommentMapper;
import org.linlinjava.litemall.db.domain.LitemallComment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallCommentService extends CommonService<LitemallCommentMapper, LitemallComment> {

    public Page<LitemallComment> queryGoodsByGid(Integer id, int offset, int limit) {
        Page<LitemallComment> pa = page(new Page<>(offset, limit), new QueryWrapper<LitemallComment>().eq("value_id", id).eq("type", 0).eq("deleted", false).orderBy(Boolean.TRUE, false, "add_time"));
        return pa;
    }

    public List<LitemallComment> query(Byte type, Integer valueId, Integer showType, Integer offset, Integer limit) {
        QueryWrapper<LitemallComment> wrapper = new QueryWrapper<>();
        if (showType == 0) {
            wrapper.eq("value_id", valueId).eq("type", type).eq("deleted", false);
        } else if (showType == 1) {
            wrapper.eq("value_id", valueId).eq("type", type).eq("deleted", false).eq("has_picture", true);
        } else {
            throw new RuntimeException("showType不支持");
        }
        return paging(wrapper, offset, limit, null, null, Boolean.TRUE);
    }

    public int count(Byte type, Integer valueId, Integer showType) {
        QueryWrapper<LitemallComment> wrapper = new QueryWrapper<>();
        if (showType == 0) {
            wrapper.eq("value_id", valueId).eq("type", type).eq("deleted", false);
        } else if (showType == 1) {
            wrapper.eq("value_id", valueId).eq("type", type).eq("deleted", false).eq("has_picture", true);
        } else {
            throw new RuntimeException("showType不支持");
        }
        return (int) count(wrapper);
    }

    public List<LitemallComment> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallComment> wrapper = new QueryWrapper<>();
        // type=2 是订单商品回复，这里过滤
        wrapper.ne("type", 2);

        if (!StringUtils.isEmpty(userId)) {
            wrapper.eq("user_id", Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(valueId)) {
            wrapper.eq("value_id", Integer.valueOf(valueId)).eq("type", (byte) 0);
        }
        return paging(wrapper, page, size, sort, order);
    }

}
