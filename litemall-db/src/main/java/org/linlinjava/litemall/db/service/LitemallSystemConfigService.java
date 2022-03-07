package org.linlinjava.litemall.db.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.linlinjava.litemall.db.dao.LitemallSystemMapper;
import org.linlinjava.litemall.db.domain.LitemallSystem;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LitemallSystemConfigService extends CommonService<LitemallSystemMapper, LitemallSystem> {

    public Map<String, String> queryAll() {
        List<LitemallSystem> systemList = all();
        Map<String, String> systemConfigs = new HashMap<>();
        for (LitemallSystem item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }
        return systemConfigs;
    }

    public Map<String, String> listMail() {
        List<LitemallSystem> systemList = list(new QueryWrapper<LitemallSystem>().likeRight("key_name", "litemall_mall_").eq("deleted", false));
        Map<String, String> data = new HashMap<>();
        for(LitemallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listWx() {
        List<LitemallSystem> systemList = list(new QueryWrapper<LitemallSystem>().likeRight("key_name", "litemall_wx_").eq("deleted", false));
        Map<String, String> data = new HashMap<>();
        for(LitemallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listOrder() {
        List<LitemallSystem> systemList = list(new QueryWrapper<LitemallSystem>().likeRight("key_name", "litemall_order_").eq("deleted", false));
        Map<String, String> data = new HashMap<>();
        for(LitemallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listExpress() {
        List<LitemallSystem> systemList = list(new QueryWrapper<LitemallSystem>().likeRight("key_name", "litemall_express_").eq("deleted", false));
        Map<String, String> data = new HashMap<>();
        for(LitemallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            update(new UpdateWrapper<LitemallSystem>().set("key_name", entry.getKey()).set("key_value", entry.getValue()).set("update_time", LocalDateTime.now()).eq("key_name", entry.getKey()).eq("deleted", false));
        }
    }

    public void addConfig(String key, String value) {
        LitemallSystem system = new LitemallSystem();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        save(system);
    }
}
