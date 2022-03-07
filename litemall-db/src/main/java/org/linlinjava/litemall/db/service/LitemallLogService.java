package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallLogMapper;
import org.linlinjava.litemall.db.domain.LitemallLog;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallLogService extends CommonService<LitemallLogMapper, LitemallLog> {

    public List<LitemallLog> querySelective(String name, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallLog> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("admin", name);
        }
        return paging(wrapper, page, size, sort, order);
    }
}
