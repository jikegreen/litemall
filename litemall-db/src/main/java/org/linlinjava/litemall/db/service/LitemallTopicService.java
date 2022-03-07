package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.linlinjava.litemall.db.dao.LitemallTopicMapper;
import org.linlinjava.litemall.db.domain.LitemallTopic;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class LitemallTopicService extends CommonService<LitemallTopicMapper, LitemallTopic> {
    private String[] columns = {"id", "title", "subtitle", "price", "pic_url", "read_count"};

    public List<LitemallTopic> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    public List<LitemallTopic> queryList(int offset, int limit, String sort, String order) {
        QueryWrapper<LitemallTopic> wrapper = new QueryWrapper<>();
        wrapper.select(columns);
        return paging(wrapper, offset, limit, sort, order);
    }

    public int queryTotal() {
        return (int) count();
    }

    public LitemallTopic findById(Integer id) {
        return findById(id, Boolean.FALSE);
    }

    public List<LitemallTopic> queryRelatedList(Integer id, int offset, int limit) {
        LitemallTopic topic = this.findById(id);
        if (Objects.isNull(topic)) {
            return queryList(offset, limit, "add_time", "desc");
        }
        List<LitemallTopic> relateds = paging(new QueryWrapper<LitemallTopic>().ne("id", id), offset, limit, null, null);
        if (relateds.size() != 0) {
            return relateds;
        }
        return queryList(offset, limit, "add_time", "desc");
    }

    public List<LitemallTopic> querySelective(String title, String subtitle, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallTopic> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(subtitle)) {
            wrapper.like("subtitle", subtitle);
        }
        return paging(wrapper, page, limit, sort, order);
    }

}
