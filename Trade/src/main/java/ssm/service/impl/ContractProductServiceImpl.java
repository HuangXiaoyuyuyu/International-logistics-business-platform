package ssm.service.impl;

import org.springframework.stereotype.Service;
import ssm.dao.ContractProductDao;
import ssm.dao.ExtCproductDao;
import ssm.model.ContractProduct;
import ssm.pagination.Page;
import ssm.service.ContractProductService;
import ssm.utils.UtilFuns;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ContractProductServiceImpl implements ContractProductService {
    @Resource
    private ContractProductDao contractProductDao;

    @Resource
    private ExtCproductDao extCproductDao;

    public List<ContractProduct> findPage(Page page) {
        return null;
    }

    public List<ContractProduct> find(Map paraMap) {
        return contractProductDao.find(paraMap);
    }

    public ContractProduct get(Serializable id) {
        return contractProductDao.get(id);
    }

    public void insert(ContractProduct contractProduct) {
        contractProduct.setId(UUID.randomUUID().toString());
        //自动设置总金额=数量*单价
        if(UtilFuns.isNotEmpty(contractProduct.getCnumber()) && UtilFuns.isNotEmpty(contractProduct.getPrice())) {
            contractProduct.setAmount(contractProduct.getCnumber()*contractProduct.getPrice());
        }
        contractProductDao.insert(contractProduct);
    }

    public void update(ContractProduct contractProduct) {
        //自动设置总金额=数量*单价
        contractProduct.setAmount(contractProduct.getCnumber()*contractProduct.getPrice());
        contractProductDao.update(contractProduct);
    }

    public void deleteById(Serializable id) {
        Serializable[] ids = {id};
        extCproductDao.deleteByContractProductById(ids);//删除货物下的所有附件
        contractProductDao.deleteById(id);
    }

    public void delete(Serializable[] ids) {
        extCproductDao.deleteByContractProductById(ids);//删除货物下的所有附件
        contractProductDao.delete(ids);
    }
}
