package org.linlinjava.litemall.db.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallOrder;
import org.linlinjava.litemall.db.domain.OrderVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderMapper {
    int updateWithOptimisticLocker(@Param("lastUpdateTime") LocalDateTime lastUpdateTime, @Param("order") LitemallOrder order);
    IPage<Map> getOrderIds(Page page, @Param("query") String query, @Param("orderByClause") String orderByClause);
    List<OrderVo> getOrderList(@Param("query") String query, @Param("orderByClause") String orderByClause);
}
