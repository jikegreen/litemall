package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.linlinjava.litemall.db.dao.LitemallSearchHistoryMapper;
import org.linlinjava.litemall.db.domain.LitemallSearchHistory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallSearchHistoryService extends CommonService<LitemallSearchHistoryMapper, LitemallSearchHistory> {
    public List<LitemallSearchHistory> queryByUid(int uid) {
        return list(new QueryWrapper<LitemallSearchHistory>().select("DISTINCT keyword as k, *").eq("user_id", uid).eq("deleted", false));
    }

    public void deleteByUid(int uid) {
        remove(new UpdateWrapper<LitemallSearchHistory>().eq("user_id", uid));
    }

    public List<LitemallSearchHistory> querySelective(String userId, String keyword, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallSearchHistory> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userId)) {
            wrapper.eq("user_id", Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like("keyword", keyword);
        }
        return paging(wrapper, page, size, sort, order);
    }
}
