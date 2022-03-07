package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallAdMapper;
import org.linlinjava.litemall.db.domain.LitemallAd;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallAdService extends CommonService<LitemallAdMapper, LitemallAd> {

    public List<LitemallAd> queryIndex() {
        return list(new QueryWrapper<LitemallAd>().eq("position", 1).eq("deleted", false).eq("enabled", true));
    }

    public List<LitemallAd> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallAd> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(content)) {
            wrapper.like("content", content);
        }
        return paging(wrapper, page, limit, sort, order);
    }

}
