package ssm.controller.cargo.contract;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ssm.controller.BaseController;
import ssm.model.ExtCproduct;
import ssm.model.Factory;
import ssm.model.SysCode;
import ssm.service.ExtCproductService;
import ssm.service.FactoryService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExtCproductController extends BaseController {

    @Resource
    private ExtCproductService extCproductService;

    @Resource
    private FactoryService factoryService;

    //列表
    @RequestMapping("/cargo/extcproduct/tocreate.action")
    public String tocreate(String contractProductId, Model model) {
        model.addAttribute("contractProductId",contractProductId);
        //准备列表数据
        Map map = new HashMap();
        map.put("contractProductId",contractProductId);
        List<ExtCproduct> dataList = extCproductService.find(map);
        model.addAttribute("dataList",dataList);
        //准备工厂
        List<Factory> factoryList = factoryService.getFactoryList();
        model.addAttribute("factoryList",factoryList);
        //准备分类下拉列表
        List<SysCode> ctypeList = extCproductService.getCtypeList();
        model.addAttribute("ctypeList",ctypeList);
        return "/cargo/contract/ExtCproductCreate.jsp";
    }

    //插入
    @RequestMapping("/cargo/extcproduct/insert.action")
    public String insert(ExtCproduct extCproduct,Model model) {
        extCproductService.insert(extCproduct);
        model.addAttribute("contractProductId",extCproduct.getContractProductId());

        return "redirect:/cargo/extcproduct/tocreate.action";
    }

    //转向修改页面
    @RequestMapping("/cargo/extcproduct/toupdate.action")
    public String toupdate(String id,Model model) {
        ExtCproduct obj = extCproductService.get(id);
        model.addAttribute("obj",obj);
        //准备工厂
        List<Factory> factoryList = factoryService.getFactoryList();
        model.addAttribute("factoryList",factoryList);
        //准备分类下拉列表
        List<SysCode> ctypeList = extCproductService.getCtypeList();
        model.addAttribute("ctypeList",ctypeList);
        return "/cargo/contract/ExtCproductUpdate.jsp";
    }

    //修改
    @RequestMapping("/cargo/extcproduct/update.action")
    public String update(ExtCproduct extCproduct,Model model) {
        extCproductService.update(extCproduct);
        model.addAttribute("contractProductId",extCproduct.getContractProductId());//传递主表ID
        return "redirect:/cargo/extcproduct/tocreate.action";
    }

    //删除
    @RequestMapping("/cargo/extcproduct/deleteById.action")
    public String deleteById(String id,String contractProductId,Model model) {
        extCproductService.deleteById(id);
        model.addAttribute("contractProductId",contractProductId);
        return "redirect:/cargo/extcproduct/tocreate.action";
    }
}
