package org.linlinjava.litemall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallGrouponRulesMapper;
import org.linlinjava.litemall.db.domain.LitemallGrouponRules;
import org.linlinjava.litemall.db.util.GrouponConstant;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallGrouponRulesService extends CommonService<LitemallGrouponRulesMapper, LitemallGrouponRules> {

    public void createRules(LitemallGrouponRules rules) {
        add(rules);
    }

    /**
     * 查询某个商品关联的团购规则
     *
     * @param goodsId
     * @return
     */
    public List<LitemallGrouponRules> queryByGoodsId(Integer goodsId) {
        return list(new QueryWrapper<LitemallGrouponRules>().eq("goods_id", goodsId).eq("status", GrouponConstant.RULE_STATUS_ON).eq("deleted", false));
    }

    public int countByGoodsId(Integer goodsId) {
        return (int)count(new QueryWrapper<LitemallGrouponRules>().eq("goods_id", goodsId).eq("status", GrouponConstant.RULE_STATUS_ON).eq("deleted", false));
    }

    public List<LitemallGrouponRules> queryByStatus(Integer status) {
        return list(new QueryWrapper<LitemallGrouponRules>().eq("status", status).eq("deleted", false));
    }

    /**
     * 获取首页团购规则列表
     *
     * @param page
     * @param limit
     * @return
     */
    public List<LitemallGrouponRules> queryList(Integer page, Integer limit) {
        return queryList(page, limit, "add_time", "desc");
    }

    public List<LitemallGrouponRules> queryList(Integer page, Integer limit, String sort, String order) {
        return paging(new QueryWrapper<LitemallGrouponRules>().eq("status", GrouponConstant.RULE_STATUS_ON).eq("deleted", false), page, limit, sort, order);
    }

    /**
     * 判断某个团购规则是否已经过期
     *
     * @return
     */
    public boolean isExpired(LitemallGrouponRules rules) {
        return (rules == null || rules.getExpireTime().isBefore(LocalDateTime.now()));
    }

    /**
     * 获取团购规则列表
     *
     * @param goodsId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallGrouponRules> querySelective(String goodsId, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallGrouponRules> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(goodsId)) {
            wrapper.eq("goods_id", Integer.parseInt(goodsId));
        }
        return paging(wrapper, page, size, sort, order);
    }

}
