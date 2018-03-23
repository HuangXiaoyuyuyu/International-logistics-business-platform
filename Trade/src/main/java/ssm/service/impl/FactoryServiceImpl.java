package ssm.service.impl;

import org.springframework.stereotype.Service;
import ssm.dao.FactoryDao;
import ssm.model.Factory;
import ssm.pagination.Page;
import ssm.service.FactoryService;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FactoryServiceImpl implements FactoryService {
    @Resource
    private FactoryDao factoryDao;

    public List<Factory> findPage(Page page) {
        return null;
    }

    public List<Factory> find(Map paraMap) {
        return factoryDao.find(paraMap);
    }

    public Factory get(Serializable id) {
        return factoryDao.get(id);
    }

    public void insert(Factory factory) {
        factory.setId(UUID.randomUUID().toString());//设置ID为UUID
        factory.setState("1");//默认为启用状态
        factoryDao.insert(factory);
    }

    public void update(Factory factory) {
        factoryDao.update(factory);
    }

    public void deleteById(Serializable id) {
        factoryDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        factoryDao.delete(ids);
    }

    public void start(Serializable[] ids) {
        Map map = new HashMap();
        map.put("state",1);         //启用
        map.put("ids",ids);
        factoryDao.updateState(map);
    }

    public void stop(Serializable[] ids) {
        Map map = new HashMap();
        map.put("state",0);         //停用
        map.put("ids",ids);
        factoryDao.updateState(map);
    }

    public List<Factory> getFactoryList() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("state",1);
        return factoryDao.find(map);
    }
}
