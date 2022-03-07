package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.linlinjava.litemall.db.dao.LitemallCollectMapper;
import org.linlinjava.litemall.db.domain.LitemallCollect;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LitemallCollectService extends CommonService<LitemallCollectMapper, LitemallCollect> {

    public int count(int uid, byte type, Integer gid) {
        return (int) count(new QueryWrapper<LitemallCollect>().eq("user_id", uid).eq("type", type).eq("value_id", gid).eq("deleted", false));
    }

    public List<LitemallCollect> queryByType(Integer userId, Byte type, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallCollect> wrapper = new QueryWrapper<>();
        if (type != null) {
            wrapper.eq("type", type);
        }
        wrapper.eq("user_id", userId);
        return paging(wrapper, page, limit, sort, order);
    }

    public int countByType(Integer userId, Byte type) {
        return (int) count(new QueryWrapper<LitemallCollect>().eq("user_id", userId).eq("type", type).eq("deleted", false));
    }

    public LitemallCollect queryByTypeAndValue(Integer userId, Byte type, Integer valueId) {
        return getOne(new QueryWrapper<LitemallCollect>().eq("user_id", userId).eq("type", type).eq("value_id", valueId).eq("deleted", false));
    }

    public List<LitemallCollect> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallCollect> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userId)) {
            wrapper.eq("user_id", Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(valueId)) {
            wrapper.eq("value_id", Integer.valueOf(valueId));
        }
        return paging(wrapper, page, size, sort, order);
    }
}
