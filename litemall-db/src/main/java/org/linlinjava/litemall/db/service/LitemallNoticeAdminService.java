package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.linlinjava.litemall.db.dao.LitemallNoticeAdminMapper;
import org.linlinjava.litemall.db.domain.LitemallNoticeAdmin;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallNoticeAdminService extends CommonService<LitemallNoticeAdminMapper, LitemallNoticeAdmin> {
    public List<LitemallNoticeAdmin> querySelective(String title, String type, Integer adminId, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallNoticeAdmin> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(title)){
            wrapper.like("notice_title", title);
        }
        if(type.equals("read")){
         wrapper.isNotNull("read_time");
        }
        else if(type.equals("unread")){
            wrapper.isNull("read_time");
        }
        wrapper.eq("admin_id", adminId);
        return paging(wrapper, page, limit, sort, order);
    }

    public LitemallNoticeAdmin find(Integer noticeId, Integer adminId) {
        return getOne(new QueryWrapper<LitemallNoticeAdmin>().eq("notice_id", noticeId).eq("admin_id", adminId).eq("deleted", false));
    }

    public void markReadByIds(List<Integer> ids, Integer adminId) {
        LocalDateTime now = LocalDateTime.now();
        update(new UpdateWrapper<LitemallNoticeAdmin>().set("read_time", now).set("update_time", now).in("id", ids).eq("admin_id", adminId).eq("deleted", false));
    }

    public void deleteById(Integer id, Integer adminId) {
        update(new UpdateWrapper<LitemallNoticeAdmin>().set("update_time", LocalDateTime.now()).set("deleted", Boolean.TRUE).eq("id", id).eq("admin_id", adminId).eq("deleted", false));
    }

    public void deleteByIds(List<Integer> ids, Integer adminId) {
        update(new UpdateWrapper<LitemallNoticeAdmin>().set("update_time", LocalDateTime.now()).set("deleted", Boolean.TRUE).in("id", ids).eq("admin_id", adminId).eq("deleted", false));
    }

    public int countUnread(Integer adminId) {
        return (int)count(new QueryWrapper<LitemallNoticeAdmin>().eq("admin_id", adminId).isNull("read_time").eq("deleted", false));
    }

    public List<LitemallNoticeAdmin> queryByNoticeId(Integer noticeId) {
        return list(new QueryWrapper<LitemallNoticeAdmin>().eq("notice_id", noticeId).eq("deleted", false));
    }

    public void deleteByNoticeId(Integer id) {
        update(new UpdateWrapper<LitemallNoticeAdmin>().set("update_time", LocalDateTime.now()).set("deleted", Boolean.TRUE).eq("notice_id", id).eq("deleted", false));
    }

    public void deleteByNoticeIds(List<Integer> ids) {
        update(new UpdateWrapper<LitemallNoticeAdmin>().set("update_time", LocalDateTime.now()).set("deleted", Boolean.TRUE).in("notice_id", ids).eq("deleted", false));
    }

    public int countReadByNoticeId(Integer noticeId) {
        return (int)count(new QueryWrapper<LitemallNoticeAdmin>().eq("notice_id", noticeId).eq("deleted", false).isNotNull("read_time"));
    }

    public void updateByNoticeId(LitemallNoticeAdmin noticeAdmin, Integer noticeId) {
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        update(noticeAdmin, new UpdateWrapper<LitemallNoticeAdmin>().eq("notice_id", noticeId).eq("deleted", false));
    }
}
