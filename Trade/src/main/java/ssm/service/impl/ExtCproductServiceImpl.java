package ssm.service.impl;

import org.springframework.stereotype.Service;
import ssm.dao.ExtCproductDao;
import ssm.dao.SysCodeDao;
import ssm.model.ExtCproduct;
import ssm.model.SysCode;
import ssm.pagination.Page;
import ssm.service.ExtCproductService;
import ssm.utils.UtilFuns;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class ExtCproductServiceImpl implements ExtCproductService {

    @Resource
    private ExtCproductDao extCproductDao;

    @Resource
    private SysCodeDao sysCodeDao;

    public List<ExtCproduct> findPage(Page page) {
        return null;
    }

    public List<ExtCproduct> find(Map paraMap) {
        return extCproductDao.find(paraMap);
    }

    public ExtCproduct get(Serializable id) {
        return extCproductDao.get(id);
    }

    public void insert(ExtCproduct extCproduct) {
        extCproduct.setId(UUID.randomUUID().toString());
        //自动设置总金额=数量*单价
        if(UtilFuns.isNotEmpty(extCproduct.getCnumber()) && UtilFuns.isNotEmpty(extCproduct.getPrice())) {
            extCproduct.setAmount(extCproduct.getCnumber()*extCproduct.getPrice());
        }
        extCproductDao.insert(extCproduct);
    }

    public void update(ExtCproduct extCproduct) {
        //自动设置总金额=数量*单价
        if(UtilFuns.isNotEmpty(extCproduct.getCnumber()) && UtilFuns.isNotEmpty(extCproduct.getPrice())) {
            extCproduct.setAmount(extCproduct.getCnumber()*extCproduct.getPrice());
        }
        extCproductDao.update(extCproduct);
    }

    public void deleteById(Serializable id) {
        extCproductDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        extCproductDao.delete(ids);
    }

    public List<SysCode> getCtypeList() {
        Map map = new HashMap();
        map.put("parentId","0104");             //sysCode_b 附件分类
        return sysCodeDao.find(map);
    }
}
