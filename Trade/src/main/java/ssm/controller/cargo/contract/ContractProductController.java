package ssm.controller.cargo.contract;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ssm.controller.BaseController;
import ssm.model.ContractProduct;
import ssm.model.Factory;
import ssm.service.ContractProductService;
import ssm.service.FactoryService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ContractProductController extends BaseController {

    @Resource
    private ContractProductService contractProductService;

    @Resource
    private FactoryService factoryService;

    //转向新增
    @RequestMapping("/cargo/contractproduct/tocreate.action")
    public String tocreate(String contractId, Model model) {                 // 传递主表ID
        model.addAttribute("contractId",contractId);
        //准备生产厂家的下拉列表
        List<Factory> factoryList = factoryService.getFactoryList();
        model.addAttribute("factoryList",factoryList);
        //某个合同下的货物列表
        Map map = new HashMap();
        map.put("contractId",contractId);
        List<ContractProduct> dataList = contractProductService.find(map);
        model.addAttribute("dataList",dataList);
        return "/cargo/contract/ContractProductCreate.jsp";     //货物的新增页面
    }

    //新增保存
    @RequestMapping("/cargo/contractproduct/insert.action")
    public String insert(ContractProduct contractProduct,Model model) {
        contractProductService.insert(contractProduct);
        model.addAttribute("contractId",contractProduct.getContractId());
        return "redirect:/cargo/contractproduct/tocreate.action";    //新增完转向新增页面
    }

    //转向修改页面
    @RequestMapping("/cargo/contractproduct/toupdate.action")
    public String toupdate(String id,Model model) {
        ContractProduct obj = contractProductService.get(id);
        model.addAttribute("obj",obj);
        //准备生产厂家的下拉列表
        List<Factory> factoryList = factoryService.getFactoryList();
        model.addAttribute("factoryList",factoryList);
        return "/cargo/contract/ContractProductUpdate.jsp";
    }

    //修改
    @RequestMapping("/cargo/contractproduct/update.action")
    public String update(ContractProduct contractProduct) {
        contractProductService.update(contractProduct);
        return "redirect:/cargo/contractproduct/tocreate.action";
    }

    //删除
    @RequestMapping("/cargo/contarctproduct/deleteById.action")
    public String deleteById(String id,String contractId,Model model) {
        contractProductService.deleteById(id);
        model.addAttribute("contractId",contractId);
        return "redirect:/cargo/contractproduct/tocreate.action";
    }
}
