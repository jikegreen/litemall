package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallAdminMapper;
import org.linlinjava.litemall.db.domain.LitemallAdmin;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallAdminService extends CommonService<LitemallAdminMapper, LitemallAdmin> {
    private final String[] result = {"id", "username", "avatar", "role_ids"};

    public List<LitemallAdmin> findAdmin(String username) {
        return list(new QueryWrapper<LitemallAdmin>().eq("username", username).eq("deleted", false));
    }

    public List<LitemallAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallAdmin> wrapper = new QueryWrapper<>();
        wrapper.select(result);
        if (!StringUtils.isEmpty(username)) {
            wrapper.like("username", username);
        }
        return paging(wrapper, page, limit, sort, order);
    }

}
