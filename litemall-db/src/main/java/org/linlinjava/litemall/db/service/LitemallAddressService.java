package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.linlinjava.litemall.db.dao.LitemallAddressMapper;
import org.linlinjava.litemall.db.domain.LitemallAddress;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallAddressService extends CommonService<LitemallAddressMapper, LitemallAddress> {

    public List<LitemallAddress> queryByUid(Integer uid) {
        return list(new QueryWrapper<LitemallAddress>().eq("user_id", uid).eq("deleted", false));
    }

    public LitemallAddress query(Integer userId, Integer id) {
        return getOne(new QueryWrapper<LitemallAddress>().eq("id", id).eq("user_id", userId).eq("deleted", false));
    }

    public LitemallAddress findDefault(Integer userId) {
        return getOne(new QueryWrapper<LitemallAddress>().eq("user_id", userId).eq("deleted", false).eq("is_default", true));
    }

    public void resetDefault(Integer userId) {
        update(new UpdateWrapper<LitemallAddress>().set("is_default", false).eq("user_id", userId).eq("deleted", false));
    }

    public List<LitemallAddress> querySelective(Integer userId, String name, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallAddress> wrapper = new QueryWrapper<>();
        if (userId != null) {
            wrapper.eq("user_id", userId);
        }
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        return paging(wrapper, page, limit, sort, order);
    }
}
