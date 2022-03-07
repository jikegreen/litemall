package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.linlinjava.litemall.db.dao.LitemallCategoryMapper;
import org.linlinjava.litemall.db.domain.LitemallCategory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LitemallCategoryService extends CommonService<LitemallCategoryMapper, LitemallCategory> {
    private String[] CHANNEL = {"id", "name", "icon_url"};

    public List<LitemallCategory> queryL1WithoutRecommend(int offset, int limit) {
        Page<LitemallCategory> pa = page(new Page<>(offset, limit), new QueryWrapper<LitemallCategory>().eq("level", "L1").ne("name", "推荐").eq("deleted", false));
        return pa.getSize()>0? pa.getRecords() : new ArrayList<>();
    }

    public List<LitemallCategory> queryL1(int offset, int limit) {
        Page<LitemallCategory> pa = page(new Page<>(offset, limit), new QueryWrapper<LitemallCategory>().eq("level", "L1").eq("deleted", false));
        return pa.getSize()>0? pa.getRecords() : new ArrayList<>();
    }

    public List<LitemallCategory> queryL1() {
        return list(new QueryWrapper<LitemallCategory>().eq("level", "L1").eq("deleted", false));
    }

    public List<LitemallCategory> queryByPid(Integer pid) {
        return list(new QueryWrapper<LitemallCategory>().eq("pid", pid).eq("deleted", false));
    }

    public List<LitemallCategory> queryL2ByIds(List<Integer> ids) {
        return list(new QueryWrapper<LitemallCategory>().in("id", ids).eq("level", "L2").eq("deleted", false));
    }

    public List<LitemallCategory> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallCategory> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)) {
            wrapper.eq("id", Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        return paging(wrapper, page, size, sort, order);
    }

    public List<LitemallCategory> queryChannel() {
        return list(new QueryWrapper<LitemallCategory>().select(CHANNEL).eq("level", "L1").eq("deleted", false));
    }
}
