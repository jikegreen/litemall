package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallPermissionMapper;
import org.linlinjava.litemall.db.domain.LitemallPermission;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LitemallPermissionService extends CommonService<LitemallPermissionMapper, LitemallPermission> {
    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }
        List<LitemallPermission> permissionList = list(new QueryWrapper<LitemallPermission>().in("role_id", roleIds).eq("deleted", false));
        for(LitemallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }
        return permissions;
    }

    public Set<String> queryByRoleId(Integer roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }
        List<LitemallPermission> permissionList = list(new QueryWrapper<LitemallPermission>().eq("role_id", roleId).eq("deleted", false));
        for(LitemallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }
        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId) {
        if(roleId == null){
            return false;
        }
        return count(new QueryWrapper<LitemallPermission>().eq("role_id", roleId).eq("permission", "*").eq("deleted", false))>0;
    }

    public void deleteByRoleId(Integer roleId) {
        remove(new QueryWrapper<LitemallPermission>().eq("role_id", roleId).eq("deleted", false));
    }

}
