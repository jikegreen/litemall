package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallIssueMapper;
import org.linlinjava.litemall.db.domain.LitemallIssue;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallIssueService extends CommonService<LitemallIssueMapper, LitemallIssue> {

    public List<LitemallIssue> querySelective(String question, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallIssue> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(question)) {
            wrapper.like("question", question);
        }
        return paging(wrapper, page, limit, sort, order);
    }
}
