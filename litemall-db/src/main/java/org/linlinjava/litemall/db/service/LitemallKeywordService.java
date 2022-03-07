package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallKeywordMapper;
import org.linlinjava.litemall.db.domain.LitemallKeyword;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallKeywordService extends CommonService<LitemallKeywordMapper, LitemallKeyword> {
    public LitemallKeyword queryDefault() {
        return getOne(new QueryWrapper<LitemallKeyword>().eq("is_default", true).eq("deleted", false));
    }

    public List<LitemallKeyword> queryHots() {
        return list(new QueryWrapper<LitemallKeyword>().eq("is_hot", true).eq("deleted", false));
    }

    public List<LitemallKeyword> queryByKeyword(String keyword, Integer page, Integer limit) {
        return paging(new QueryWrapper<LitemallKeyword>().select("Distinct keyword, *").like("keyword", keyword).eq("deleted", false), page, limit, null, null);
    }

    public List<LitemallKeyword> querySelective(String keyword, String url, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallKeyword> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like("keyword", keyword);
        }
        if (!StringUtils.isEmpty(url)) {
            wrapper.like("url", url);
        }
        return paging(wrapper, page, limit, sort, order);
    }

}
