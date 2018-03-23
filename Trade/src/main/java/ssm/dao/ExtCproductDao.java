package ssm.dao;

import ssm.model.ExtCproduct;

import java.io.Serializable;

public interface ExtCproductDao extends BaseDao<ExtCproduct> {
    public void deleteByContractProductById(Serializable ids);
    public void deleteByContractId(Serializable ids);
}
