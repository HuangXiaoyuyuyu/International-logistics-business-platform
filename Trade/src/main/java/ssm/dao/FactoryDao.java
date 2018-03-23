package ssm.dao;

import ssm.model.Factory;

import java.util.Map;

public interface FactoryDao extends BaseDao<Factory> {

    public void updateState(Map map);
}
