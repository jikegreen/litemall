package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.linlinjava.litemall.db.dao.LitemallRegionMapper;
import org.linlinjava.litemall.db.domain.LitemallRegion;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LitemallRegionService extends CommonService<LitemallRegionMapper, LitemallRegion> {
    public List<LitemallRegion> getAll(){
        return list(new QueryWrapper<LitemallRegion>().ne("type", 4));
    }

    public List<LitemallRegion> queryByPid(Integer parentId) {
        return list(new QueryWrapper<LitemallRegion>().eq("pid", parentId));
    }

    public List<LitemallRegion> querySelective(String name, Integer code, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallRegion> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(code)) {
            wrapper.eq("code", code);
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            wrapper.orderBy(Boolean.TRUE, "asc".equals(order), sort);
        }
        Page<LitemallRegion> pa = page(new Page<>(page, size), wrapper);
        return pa.getSize()>0? pa.getRecords() : new ArrayList<>();
    }

}
