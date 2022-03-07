package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallFeedbackMapper;
import org.linlinjava.litemall.db.domain.LitemallFeedback;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Yogeek
 * @date 2018/8/27 11:39
 */
@Service
public class LitemallFeedbackService extends CommonService<LitemallFeedbackMapper, LitemallFeedback> {

    public List<LitemallFeedback> querySelective(Integer userId, String username, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallFeedback> wrapper = new QueryWrapper<>();
        if (userId != null) {
            wrapper.eq("user_id", userId);
        }
        if (!StringUtils.isEmpty(username)) {
            wrapper.like("username", username);
        }
        return paging(wrapper, page, limit, sort, order);
    }
}
