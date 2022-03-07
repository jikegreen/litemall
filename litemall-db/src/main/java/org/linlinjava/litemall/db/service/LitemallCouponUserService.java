package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallCouponUserMapper;
import org.linlinjava.litemall.db.domain.LitemallCouponUser;
import org.linlinjava.litemall.db.util.CouponUserConstant;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class LitemallCouponUserService extends CommonService<LitemallCouponUserMapper, LitemallCouponUser> {

    public Integer countCoupon(Integer couponId) {
        return (int)count(new QueryWrapper<LitemallCouponUser>().eq("coupon_id", couponId).eq("deleted", false));
    }

    public Integer countUserAndCoupon(Integer userId, Integer couponId) {
        return (int)count(new QueryWrapper<LitemallCouponUser>().eq("user_id", userId).eq("coupon_id", couponId).eq("deleted", false));
    }

    public List<LitemallCouponUser> queryList(Integer userId, Integer couponId, Integer status, Integer page, Integer size, String sort, String order) {
        QueryWrapper<LitemallCouponUser> wrapper = new QueryWrapper<>();
        if (userId != null) {
            wrapper.eq("user_id", userId);
        }
        if(couponId != null){
            wrapper.eq("coupon_id", couponId);
        }
        if (status != null) {
            wrapper.eq("status", status);
        }
        if (Objects.isNull(page) || Objects.isNull(size)) {
            return list(wrapper.orderBy(Boolean.TRUE, "asc".equals(order), sort));
        }
        return paging(wrapper, page, size, sort, order);
    }

    public List<LitemallCouponUser> queryAll(Integer userId, Integer couponId) {
        return queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
    }

    public List<LitemallCouponUser> queryAll(Integer userId) {
        return queryList(userId, null, CouponUserConstant.STATUS_USABLE, null, null, "add_time", "desc");
    }

    public LitemallCouponUser queryOne(Integer userId, Integer couponId) {
        List<LitemallCouponUser> couponUserList = queryList(userId, couponId, CouponUserConstant.STATUS_USABLE, 1, 1, "add_time", "desc");
        if(couponUserList.size() == 0){
            return null;
        }
        return couponUserList.get(0);
    }

    public List<LitemallCouponUser> queryExpired() {
        return list(new QueryWrapper<LitemallCouponUser>().eq("status", CouponUserConstant.STATUS_USABLE).le("end_time", LocalDateTime.now()).eq("deleted", false));
    }

    public List<LitemallCouponUser> findByOid(Integer orderId) {
        return list(new QueryWrapper<LitemallCouponUser>().eq("order_id", orderId).eq("deleted", false));
    }
}
