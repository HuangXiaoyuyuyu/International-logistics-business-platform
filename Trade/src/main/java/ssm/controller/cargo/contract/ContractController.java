package ssm.controller.cargo.contract;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssm.controller.BaseController;
import ssm.model.Contract;
import ssm.service.ContractService;
import ssm.vo.ContractVO;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ContractController extends BaseController{

    @Resource
    private ContractService contractService;

    //列表
    @RequestMapping("/cargo/contract/list.action")
    public String list(Model model) {
        List<Contract> dataList = contractService.find(null);
        model.addAttribute("dataList",dataList);
        return "cargo/contract/ContractList.jsp";
    }

    //查看
    @RequestMapping("/cargo/contract/toview.action")
    public String toview(String id,Model model) {
        ContractVO obj = contractService.view(id);
        model.addAttribute("obj",obj);
        return "/cargo/contract/ContractView.jsp";
    }

    //转向新增页面
    @RequestMapping("/cargo/contract/tocreate.action")
    public String tocreate() {
        return "/cargo/contract/ContractCreate.jsp";
    }

    //保存新增
    @RequestMapping("/cargo/contract/insert.action")
    public String insert(Contract contract) {
        contractService.insert(contract);
        return "redirect:/cargo/contract/list.action";
    }

    //转向修改页面
    @RequestMapping("/cargo/contract/toupdate.action")
    public String toupdate(String id,Model model) {
        Contract obj = contractService.get(id);
        model.addAttribute("obj",obj);
        return "/cargo/contract/ContractUpdate.jsp";
    }

    //保存修改
    @RequestMapping("/cargo/contract/update.action")
    public String update(Contract contract) {
        contractService.update(contract);
        return "redirect:/cargo/contract/list.action";
    }

    //删除
    @RequestMapping("/cargo/contract/delete.action")
    public String delete(String[] id) {
        contractService.delete(id);
        return "redirect:/cargo/contract/list.action";
    }

    //上报
    @RequestMapping("/cargo/contract/submit.action")
    public String submit(String[] id) {
        contractService.submit(id);
        return "redirect:/cargo/contract/list.action";
    }

    //取消
    @RequestMapping("/cargo/contract/cancel.action")
    public String cancel(String[] id) {
        contractService.cancel(id);
        return "redirect:/cargo/contract/list.action";
    }
}
