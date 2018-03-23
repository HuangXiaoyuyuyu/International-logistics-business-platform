package ssm.dao;

import ssm.model.ContractProduct;

import java.io.Serializable;

public interface ContractProductDao extends BaseDao<ContractProduct> {
    public void deleteByContractId(Serializable ids);
}
