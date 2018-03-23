package ssm.service.impl;

import org.springframework.stereotype.Service;
import ssm.dao.SysCodeDao;
import ssm.model.SysCode;
import ssm.service.SysCodeService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SysCodeServiceImpl implements SysCodeService{

    @Resource
    private SysCodeDao sysCodeDao;

    public List<SysCode> find(Map paraMap) {
        return sysCodeDao.find(paraMap);
    }
}
