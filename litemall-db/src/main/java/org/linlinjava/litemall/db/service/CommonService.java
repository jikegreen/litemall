package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author kangwang
 * @Date 9:14 上午 2022/2/26
 */
public class CommonService <M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
	private static final String ADD_TIME = "addTime";
	private static final String UPDATE_TIME = "updateTime";
	private static final String DELETED = "deleted";
	private static final String ID = "id";

	@Override
	public boolean updateById(T admin) {
		try {
			FieldUtils.writeDeclaredField(admin, UPDATE_TIME, LocalDateTime.now(), Boolean.TRUE);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return super.updateById(admin);
	}

	public void deleteById(Integer id) {
		removeById(id);
	}

	public void add(T admin) {
		try {
			FieldUtils.writeDeclaredField(admin, ADD_TIME, LocalDateTime.now(), Boolean.TRUE);
			FieldUtils.writeDeclaredField(admin, UPDATE_TIME, LocalDateTime.now(), Boolean.TRUE);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		save(admin);
	}

	public T findById(Integer id, boolean... hasDel) {
		if (hasDel.length>0 && !hasDel[0]) {
			return getOne(new QueryWrapper<T>().eq(ID, id).eq(DELETED, false));
		}
		return getById(id);
	}

	public List<T> all() {
		return list(new QueryWrapper<T>().eq("deleted", false));
	}

	public boolean update(T address) {
		return saveOrUpdate(address);
	}

	public List<T> paging(QueryWrapper<T> wrapper, Integer page, Integer limit, String sort, String order, boolean... needDefaultOrderBy) {
		wrapper.eq("deleted", false);
		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
			wrapper.orderBy(Boolean.TRUE, "asc".equals(order), sort);
		}else if (needDefaultOrderBy.length>0 && needDefaultOrderBy[0]) {
			wrapper.orderBy(Boolean.TRUE, false, "add_time");
		}
		Page<T> pa = page(new Page<>(page, limit), wrapper);
		return pa.getSize()>0? pa.getRecords() : new ArrayList<>();
	}

	@Override
	public long count() {
		return count(new QueryWrapper<T>().eq("deleted", false));
	}

	public void deleteByIds(List<Integer> ids) {
		removeByIds(ids);
	}
}
