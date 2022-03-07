package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallNoticeMapper;
import org.linlinjava.litemall.db.domain.LitemallNotice;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallNoticeService extends CommonService<LitemallNoticeMapper, LitemallNotice> {
    public List<LitemallNotice> querySelective(String title, String content, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallNotice> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(content)) {
            wrapper.like("content", content);
        }
        return paging(wrapper, page, limit, sort, order);
    }

}
