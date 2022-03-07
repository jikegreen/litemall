package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallAftersaleMapper;
import org.linlinjava.litemall.db.domain.LitemallAftersale;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class LitemallAftersaleService extends CommonService<LitemallAftersaleMapper, LitemallAftersale> {

    public List<LitemallAftersale> queryList(Integer userId, Short status, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallAftersale> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (status != null) {
            wrapper.eq("status", status);
        }
        return paging(wrapper, page, limit, sort, order, Boolean.TRUE);
    }

    public List<LitemallAftersale> querySelective(Integer orderId, String aftersaleSn, Short status, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallAftersale> wrapper = new QueryWrapper<>();
        if (orderId != null) {
            wrapper.eq("order_id", orderId);
        }
        if (!StringUtils.isEmpty(aftersaleSn)) {
            wrapper.eq("aftersale_sn", aftersaleSn);
        }
        if (status != null) {
            wrapper.eq("status", status);
        }
        return paging(wrapper, page, limit, sort, order, Boolean.TRUE);
    }

    private String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public int countByAftersaleSn(Integer userId, String aftersaleSn) {
        return (int) count(new QueryWrapper<LitemallAftersale>().eq("aftersale_sn", aftersaleSn).eq("user_id", userId).eq("deleted", false));
    }

    // TODO 这里应该产生一个唯一的编号，但是实际上这里仍然存在两个售后编号相同的可能性
    public String generateAftersaleSn(Integer userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String aftersaleSn = now + getRandomNum(6);
        while (countByAftersaleSn(userId, aftersaleSn) != 0) {
            aftersaleSn = now + getRandomNum(6);
        }
        return aftersaleSn;
    }

    public void deleteByOrderId(Integer userId, Integer orderId) {
        remove(new QueryWrapper<LitemallAftersale>().eq("order_id", orderId).eq("user_id", userId).eq("deleted", false));
    }

    public LitemallAftersale findByOrderId(Integer userId, Integer orderId) {
        return getOne(new QueryWrapper<LitemallAftersale>().eq("order_id", orderId).eq("user_id", userId).eq("deleted", false));
    }
}
