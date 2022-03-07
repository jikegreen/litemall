package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallBrandMapper;
import org.linlinjava.litemall.db.domain.LitemallBrand;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallBrandService extends CommonService<LitemallBrandMapper, LitemallBrand> {
    private String[] columns = {"id", "name", "`desc`", "pic_url", "floor_price"};

    public List<LitemallBrand> query(Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallBrand> wrapper = new QueryWrapper<>();
        wrapper.select(columns);
        return paging(wrapper, page, limit, sort, order);
    }

    public List<LitemallBrand> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    public List<LitemallBrand> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallBrand> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)) {
            wrapper.eq("id", Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        return paging(wrapper, page, size, sort, order);
    }

}
