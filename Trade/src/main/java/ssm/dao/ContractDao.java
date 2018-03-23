package ssm.dao;

import ssm.model.Contract;
import ssm.vo.ContractVO;

import java.util.Map;

public interface ContractDao extends BaseDao<Contract> {

    public void updateState(Map map);

    public ContractVO view(String contractId);
}
