package ssm.service.impl;

import org.springframework.stereotype.Service;
import ssm.dao.ContractDao;
import ssm.dao.ContractProductDao;
import ssm.dao.ExtCproductDao;
import ssm.model.Contract;
import ssm.pagination.Page;
import ssm.service.ContractService;
import ssm.vo.ContractVO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class ContractServiceImpl implements ContractService {

    @Resource
    private ContractDao contractDao;

    @Resource
    private ContractProductDao contractProductDao;

    @Resource
    private ExtCproductDao extCproductDao;

    public List<Contract> findPage(Page page) {
        return null;
    }

    public List<Contract> find(Map paraMap) {
        return contractDao.find(paraMap);
    }

    public Contract get(Serializable id) {
        return contractDao.get(id);
    }

    public ContractVO view(String contractId) {
        return contractDao.view(contractId);
    }

    public void insert(Contract contract) {
        contract.setId(UUID.randomUUID().toString());//设置ID为UUID
        contract.setState(0);       //默认状态为草稿
        contractDao.insert(contract);
    }

    public void update(Contract contract) {
        contractDao.update(contract);
    }

    public void deleteById(Serializable id) {
        Serializable[] ids = {id};
        extCproductDao.deleteByContractId(ids);
        contractProductDao.deleteByContractId(ids);
        contractDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        extCproductDao.deleteByContractId(ids);
        contractProductDao.deleteByContractId(ids);
        contractDao.delete(ids);
    }

    public void submit(Serializable[] ids) {
        Map map = new HashMap();
        map.put("state",1);         //1已上报
        map.put("ids",ids);
        contractDao.updateState(map);
    }

    public void cancel(Serializable[] ids) {
        Map map = new HashMap();
        map.put("state",0);         //0草稿
        map.put("ids",ids);
        contractDao.updateState(map);
    }


}
