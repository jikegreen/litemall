package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallUserMapper;
import org.linlinjava.litemall.db.domain.LitemallUser;
import org.linlinjava.litemall.db.domain.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LitemallUserService extends CommonService<LitemallUserMapper, LitemallUser> {

    public UserVo findUserVoById(Integer userId) {
        LitemallUser user = findById(userId);
        UserVo userVo = new UserVo();
        userVo.setNickname(user.getNickname());
        userVo.setAvatar(user.getAvatar());
        return userVo;
    }

    public LitemallUser queryByOid(String openId) {
        return getOne(new QueryWrapper<LitemallUser>().eq("weixin_openid", openId).eq("deleted", false));
    }

    public List<LitemallUser> querySelective(String username, String mobile, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallUser> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            wrapper.eq("username", username);
        }
        if (!StringUtils.isEmpty(mobile)) {
            wrapper.eq("mobile", mobile);
        }
        wrapper.eq("deleted", false);
        return paging(wrapper, page, size, sort, order);
    }

    public List<LitemallUser> queryByUsername(String username) {
        return list(new QueryWrapper<LitemallUser>().eq("username", username).eq("deleted", false));
    }

    public boolean checkByUsername(String username) {
        return count(new QueryWrapper<LitemallUser>().eq("username", username).eq("deleted", false))>0;
    }

    public List<LitemallUser> queryByMobile(String mobile) {
        return list(new QueryWrapper<LitemallUser>().eq("mobile", mobile).eq("deleted", false));
    }

    public List<LitemallUser> queryByOpenid(String openid) {
        return list(new QueryWrapper<LitemallUser>().eq("weixin_openid", openid).eq("deleted", false));
    }

}
