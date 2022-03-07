package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.linlinjava.litemall.db.dao.LitemallOrderMapper;
import org.linlinjava.litemall.db.dao.OrderMapper;
import org.linlinjava.litemall.db.domain.LitemallOrder;
import org.linlinjava.litemall.db.domain.OrderVo;
import org.linlinjava.litemall.db.util.OrderUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class LitemallOrderService extends CommonService<LitemallOrderMapper, LitemallOrder> {
    @Resource
    private OrderMapper orderMapper;

    public int count(Integer userId) {
        return (int) count(new QueryWrapper<LitemallOrder>().eq("user_id", userId).eq("deleted", false));

    }

    public LitemallOrder findById(Integer userId, Integer orderId) {
        return findById(orderId, Boolean.FALSE);
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

    public int countByOrderSn(Integer userId, String orderSn) {
        return (int) count(new QueryWrapper<LitemallOrder>().eq("user_id", userId).eq("order_sn", orderSn).eq("deleted", false));
    }

    // TODO 这里应该产生一个唯一的订单，但是实际上这里仍然存在两个订单相同的可能性
    public String generateOrderSn(Integer userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String orderSn = now + getRandomNum(6);
        while (countByOrderSn(userId, orderSn) != 0) {
            orderSn = now + getRandomNum(6);
        }
        return orderSn;
    }

    public List<LitemallOrder> queryByOrderStatus(Integer userId, List<Integer> orderStatus, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        if (orderStatus != null) {
            wrapper.in("order_status", orderStatus);
        }
        return paging(wrapper, page, limit, null, null, Boolean.TRUE);
    }

    public List<LitemallOrder> querySelective(Integer userId, String orderSn, LocalDateTime start, LocalDateTime end, List<Short> orderStatusArray, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallOrder> wrapper = new QueryWrapper<>();
        if (userId != null) {
            wrapper.eq("user_id", userId);
        }
        if (!StringUtils.isEmpty(orderSn)) {
            wrapper.eq("order_sn", orderSn);
        }
        if(start != null){
            wrapper.ge("add_time", start);
        }
        if(end != null){
            wrapper.le("add_time", end);
        }
        if (orderStatusArray != null && orderStatusArray.size() != 0) {
            wrapper.in("order_status", orderStatusArray);
        }
        return paging(wrapper, page, limit, sort, order);
    }

    public int updateWithOptimisticLocker(LitemallOrder order) {
        LocalDateTime preUpdateTime = order.getUpdateTime();
        order.setUpdateTime(LocalDateTime.now());
        return orderMapper.updateWithOptimisticLocker(preUpdateTime, order);
    }

    public List<LitemallOrder> queryUnpaid(int minutes) {
        return list(new QueryWrapper<LitemallOrder>().eq("order_status", OrderUtil.STATUS_CREATE).eq("deleted", false));
    }

    public List<LitemallOrder> queryUnconfirm(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.minusDays(days);
        return list(new QueryWrapper<LitemallOrder>().eq("order_status", OrderUtil.STATUS_SHIP).lt("ship_time", expired).eq("deleted", false));
    }

    public LitemallOrder findBySn(String orderSn) {
        return getOne(new QueryWrapper<LitemallOrder>().eq("order_sn", orderSn).eq("deleted", false));
    }

    public Map<Object, Object> orderInfo(Integer userId) {
        List<LitemallOrder> orders = list(new QueryWrapper<LitemallOrder>().select("order_status, comments").eq("user_id", userId).eq("deleted", false));
        int unpaid = 0;
        int unship = 0;
        int unrecv = 0;
        int uncomment = 0;
        for (LitemallOrder order : orders) {
            if (OrderUtil.isCreateStatus(order)) {
                unpaid++;
            } else if (OrderUtil.isPayStatus(order)) {
                unship++;
            } else if (OrderUtil.isShipStatus(order)) {
                unrecv++;
            } else if (OrderUtil.isConfirmStatus(order) || OrderUtil.isAutoConfirmStatus(order)) {
                uncomment += order.getComments();
            } else {
                // do nothing
            }
        }

        Map<Object, Object> orderInfo = new HashMap<Object, Object>();
        orderInfo.put("unpaid", unpaid);
        orderInfo.put("unship", unship);
        orderInfo.put("unrecv", unrecv);
        orderInfo.put("uncomment", uncomment);
        return orderInfo;

    }

    public List<LitemallOrder> queryComment(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.minusDays(days);
        return list(new QueryWrapper<LitemallOrder>().gt("comments", 0).lt("confirm_time", expired).eq("deleted", false));
    }

    public void updateAftersaleStatus(Integer orderId, Integer statusReject) {
        LitemallOrder order = new LitemallOrder();
        order.setId(orderId);
        order.setAftersaleStatus(statusReject);
        order.setUpdateTime(LocalDateTime.now());
        updateById(order);
    }


    public Map<String, Object> queryVoSelective(String nickname, String consignee, String orderSn, LocalDateTime start, LocalDateTime end, List<Short> orderStatusArray, Integer page, Integer limit, String sort, String order) {
        List<String> querys = new ArrayList<>(4);
        if (!StringUtils.isEmpty(nickname)) {
            querys.add(" u.nickname like '%" + nickname + "%' ");
        }
        if (!StringUtils.isEmpty(consignee)) {
            querys.add(" o.consignee like '%" + consignee + "%' ");
        }
        if (!StringUtils.isEmpty(orderSn)) {
            querys.add(" o.order_sn = '" + orderSn + "' ");
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (start != null) {
            querys.add(" o.add_time >= '" + df.format(start) + "' ");
        }
        if (end != null) {
            querys.add(" o.add_time < '" + df.format(end) + "' ");
        }
        if (orderStatusArray != null && orderStatusArray.size() > 0) {
            querys.add(" o.order_status in (" + StringUtils.collectionToDelimitedString(orderStatusArray, ",") + ") ");
        }
        querys.add(" o.deleted = 0 and og.deleted = 0 ");
        String query = StringUtils.collectionToDelimitedString(querys, "and");
        String orderByClause = null;
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            orderByClause = "o." + sort + " " + order +", o.id desc ";
        }
        IPage<Map> list1 = orderMapper.getOrderIds(new Page(page, limit), query, orderByClause);
        List<Integer> ids = new ArrayList<>();
        for (Map map : list1.getRecords()) {
            Integer id = (Integer) map.get("id");
            ids.add(id);
        }

        List<OrderVo> list2 = new ArrayList<>();
        if (!ids.isEmpty()) {
            querys.add(" o.id in (" + StringUtils.collectionToDelimitedString(ids, ",") + ") ");
            query = StringUtils.collectionToDelimitedString(querys, "and");
            list2 = orderMapper.getOrderList(query, orderByClause);
        }
        Map<String, Object> data = new HashMap<String, Object>(5);
        data.put("list", list2);
        data.put("total", list1.getTotal());
        data.put("page", list1.getCurrent());
        data.put("limit", list1.getSize());
        data.put("pages", list1.getPages());
        return data;
    }
}
