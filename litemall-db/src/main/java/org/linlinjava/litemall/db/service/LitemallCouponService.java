package org.linlinjava.litemall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.linlinjava.litemall.db.dao.LitemallCouponMapper;
import org.linlinjava.litemall.db.domain.LitemallCoupon;
import org.linlinjava.litemall.db.domain.LitemallCouponUser;
import org.linlinjava.litemall.db.util.CouponConstant;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LitemallCouponService extends CommonService<LitemallCouponMapper, LitemallCoupon> {
    private final LitemallCouponUserService couponUserService;

    private String[] result = {"id", "name", "`desc`", "tag",
                                            "days", "start_time", "end_time",
                                            "discount", "min"};

    /**
     * 查询，空参数
     *
     * @param offset
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallCoupon> queryList(int offset, int limit, String sort, String order) {
        Page<LitemallCoupon> pa = page(new Page<>(offset, limit), new QueryWrapper<LitemallCoupon>().select(result).orderBy(Boolean.TRUE, "asc".equals(order), sort));
        return pa.getSize()>0? pa.getRecords() : new ArrayList<>();
    }

    /**
     * 查询
     *
     * @param
     * @param offset
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallCoupon> queryList(QueryWrapper<LitemallCoupon> wrapper, int offset, int limit, String sort, String order) {
        wrapper.eq("type", CouponConstant.TYPE_COMMON).eq("status", CouponConstant.STATUS_NORMAL).eq("deleted", false);
        Page<LitemallCoupon> pa = page(new Page<>(offset, limit), wrapper.select(result).orderBy(Boolean.TRUE, "asc".equals(order), sort));
        return pa.getSize()>0? pa.getRecords() : new ArrayList<>();
    }

    public List<LitemallCoupon> queryAvailableList(Integer userId, int offset, int limit) {
        assert userId != null;
        // 过滤掉登录账号已经领取过的coupon
        QueryWrapper<LitemallCoupon> wrapper = new QueryWrapper<>();
        List<LitemallCouponUser> used = couponUserService.list(new QueryWrapper<LitemallCouponUser>().eq("user_id", userId));
        if(used!=null && !used.isEmpty()){
            wrapper.notIn("id", used.stream().map(LitemallCouponUser::getCouponId).collect(Collectors.toList()));
        }
        return queryList(wrapper, offset, limit, "add_time", "desc");
    }

    public List<LitemallCoupon> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    public LitemallCoupon findByCode(String code) {
        List<LitemallCoupon> couponList =  list(new QueryWrapper<LitemallCoupon>().eq("code", code).eq("type", CouponConstant.TYPE_CODE).eq("Status", CouponConstant.STATUS_NORMAL).eq("deleted", false));
        if(couponList.size() > 1){
            throw new RuntimeException("");
        }
        else if(couponList.size() == 0){
            return null;
        }
        else {
            return couponList.get(0);
        }
    }

    /**
     * 查询新用户注册优惠券
     *
     * @return
     */
    public List<LitemallCoupon> queryRegister() {
        return list(new QueryWrapper<LitemallCoupon>().eq("type", CouponConstant.TYPE_REGISTER).eq("status", CouponConstant.STATUS_NORMAL).eq("deleted", false));
    }

    public List<LitemallCoupon> querySelective(String name, Short type, Short status, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallCoupon> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (type != null) {
            wrapper.eq("type", type);
        }
        if (status != null) {
            wrapper.eq("status", status);
        }
        return paging(wrapper, page, limit, sort, order);
    }

    private String getRandomNum(Integer num) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        base += "0123456789";

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成优惠码
     *
     * @return 可使用优惠码
     */
    public String generateCode() {
        String code = getRandomNum(8);
        while(findByCode(code) != null){
            code = getRandomNum(8);
        }
        return code;
    }

    /**
     * 查询过期的优惠券:
     * 注意：如果timeType=0, 即基于领取时间有效期的优惠券，则优惠券不会过期
     *
     * @return
     */
    public List<LitemallCoupon> queryExpired() {
        return list(new QueryWrapper<LitemallCoupon>().eq("status", CouponConstant.STATUS_NORMAL).eq("time_type", CouponConstant.TIME_TYPE_TIME).le("end_time", LocalDateTime.now()).eq("deleted", false));
    }
}
