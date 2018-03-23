package ssm.service.impl;

import org.springframework.stereotype.Service;
import ssm.dao.OutProductDao;
import ssm.service.OutProductService;
import ssm.vo.OutProductVO;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OutProductServiceImpl implements OutProductService {
    @Resource
    private OutProductDao outProductDao;

    public List<OutProductVO> find(String inputDate) {
        Map map = new HashMap();
        map.put("inputDate",inputDate);
        return outProductDao.find(map);
    }
}
