package ssm.service;

import ssm.model.SysCode;

import java.util.List;
import java.util.Map;

public interface SysCodeService {
    public List<SysCode> find(Map paraMap);
}
