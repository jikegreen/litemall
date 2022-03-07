package org.linlinjava.litemall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallGrouponMapper;
import org.linlinjava.litemall.db.domain.LitemallGroupon;
import org.linlinjava.litemall.db.util.GrouponConstant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LitemallGrouponService extends CommonService<LitemallGrouponMapper, LitemallGroupon> {

    /**
     * 获取用户发起的团购记录
     *
     * @param userId
     * @return
     */
    public List<LitemallGroupon> queryMyGroupon(Integer userId) {
        return list(new QueryWrapper<LitemallGroupon>().eq("user_id", userId).eq("creator_user_id", userId).eq("groupon_id", 0).ne("status", GrouponConstant.STATUS_NONE).eq("deleted", false).orderByDesc("add_time"));
    }

    /**
     * 获取用户参与的团购记录
     *
     * @param userId
     * @return
     */
    public List<LitemallGroupon> queryMyJoinGroupon(Integer userId) {
        return list(new QueryWrapper<LitemallGroupon>().eq("user_id", userId).ne("groupon_id", 0).ne("status", GrouponConstant.STATUS_NONE).eq("deleted", false).orderByDesc("add_time"));
    }

    /**
     * 根据OrderId查询团购记录
     *
     * @param orderId
     * @return
     */
    public LitemallGroupon queryByOrderId(Integer orderId) {
        return getOne(new QueryWrapper<LitemallGroupon>().eq("order_id", orderId).eq("deleted", false));
    }

    /**
     * 获取某个团购活动参与的记录
     *
     * @param id
     * @return
     */
    public List<LitemallGroupon> queryJoinRecord(Integer id) {
        return list(new QueryWrapper<LitemallGroupon>().eq("groupon_id", 0).ne("status", GrouponConstant.STATUS_NONE).eq("deleted", false).orderByDesc("add_time"));
    }

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public LitemallGroupon queryById(Integer id) {
        return findById(id, Boolean.FALSE);
    }

    /**
     * 根据ID查询记录
     *
     * @param userId
     * @param id
     * @return
     */
    public LitemallGroupon queryById(Integer userId, Integer id) {
        return findById(id, Boolean.FALSE);
    }

    /**
     * 返回某个发起的团购参与人数
     *
     * @param grouponId
     * @return
     */
    public int countGroupon(Integer grouponId) {
        return (int) count(new QueryWrapper<LitemallGroupon>().eq("groupon_id", grouponId).ne("status", GrouponConstant.STATUS_NONE).eq("deleted", false));
    }

    public boolean hasJoin(Integer userId, Integer grouponId) {
        return count(new QueryWrapper<LitemallGroupon>().eq("user_id", userId).eq("groupon_id", grouponId).ne("status", GrouponConstant.STATUS_NONE).eq("deleted", false))>0;
    }

    /**
     * 创建或参与一个团购
     *
     * @param groupon
     * @return
     */
    public void createGroupon(LitemallGroupon groupon) {
        add(groupon);
    }


    /**
     * 查询所有发起的团购记录
     *
     * @param rulesId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallGroupon> querySelective(String rulesId, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallGroupon> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(rulesId)) {
            wrapper.eq("rules_id", Integer.parseInt(rulesId));
        }
        wrapper.eq("groupon_id", 0).ne("status", GrouponConstant.STATUS_NONE).eq("deleted", false);
        return paging(wrapper, page, size, sort, order);
    }

    public List<LitemallGroupon> queryByRuleId(int grouponRuleId) {
        return list(new QueryWrapper<LitemallGroupon>().eq("rules_id", grouponRuleId).eq("deleted", false));
    }
}
