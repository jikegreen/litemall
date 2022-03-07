package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.linlinjava.litemall.db.dao.LitemallStorageMapper;
import org.linlinjava.litemall.db.domain.LitemallStorage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallStorageService extends CommonService<LitemallStorageMapper, LitemallStorage> {

    public void deleteByKey(String key) {
        remove(new UpdateWrapper<LitemallStorage>().eq("`key`", key));
    }

    public LitemallStorage findByKey(String key) {
        return getOne(new QueryWrapper<LitemallStorage>().eq("`key`", key).eq("deleted",false));
    }

    public List<LitemallStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallStorage> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.eq("`key`", key);
        }
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        return paging(wrapper, page, limit, sort, order);
    }
}
