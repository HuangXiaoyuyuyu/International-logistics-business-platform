package ssm.service;

import ssm.vo.OutProductVO;

import java.util.List;
import java.util.Map;

public interface OutProductService {
    public List<OutProductVO> find(String inputDate);				//带条件查询，条件可以为null，既没有条件；返回list对象集合
}
