package org.linlinjava.litemall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallRoleMapper;
import org.linlinjava.litemall.db.domain.LitemallRole;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LitemallRoleService extends CommonService<LitemallRoleMapper, LitemallRole> {

    public Set<String> queryByIds(Integer[] roleIds) {
        Set<String> roles = new HashSet<String>();
        if(roleIds.length == 0){
            return roles;
        }
        List<LitemallRole> roleList = list(new QueryWrapper<LitemallRole>().in("id", roleIds).eq("enabled", true).eq("deleted", false));
        for(LitemallRole role : roleList){
            roles.add(role.getName());
        }
        return roles;
    }

    public List<LitemallRole> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallRole> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        return paging(wrapper, page, limit, sort, order);
    }

    public boolean checkExist(String name) {
        return count(new QueryWrapper<LitemallRole>().eq("name", name).eq("deleted", false))>0;
    }

    public List<LitemallRole> queryAll() {
        return all();
    }
}
