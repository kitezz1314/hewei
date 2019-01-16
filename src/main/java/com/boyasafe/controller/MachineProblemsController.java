package com.boyasafe.controller;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.model.MachineProblems;
import com.boyasafe.service.IMachineProblemsService;
import com.boyasafe.commons.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-22
 */
@Controller
@RequestMapping("/machineProblems")
public class MachineProblemsController extends BaseController {

    @Autowired 
    private IMachineProblemsService machineProblemsService;
     
    @GetMapping("/manager")
    public String manager() {
        return "admin/machineProblems/machineProblemsList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(MachineProblems machineProblems, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        if (machineProblems.getMachineId() != null && !"".equals(machineProblems.getMachineId())) {
            condition.put("machineId", machineProblems.getMachineId());
        }
        if (machineProblems.getSeller() != null && !"".equals(machineProblems.getSeller())) {
            condition.put("seller", machineProblems.getSeller());
        }
        if (machineProblems.getQuestionStatus()!=null && !"".equals(machineProblems.getQuestionStatus())) {
			condition.put("questionStatus", machineProblems.getQuestionStatus());
		}
        pageInfo.setCondition(condition);
        machineProblemsService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model, String machineId) {
    	model.addAttribute("machineId", machineId);
        return "admin/machineInfo/machineProblemsAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid MachineProblems machineProblems) {
        if (machineProblems.getUpDoor()!=null && "need".equals(machineProblems.getUpDoor())) {
			machineProblems.setUpDoor("需要");
		}else if (machineProblems.getUpDoor()!=null && "noneed".equals(machineProblems.getUpDoor())) {
			machineProblems.setUpDoor("不需要");
		}
        machineProblems.setQuestionStatus("未解决");
        if(machineProblems.getFit()!=null && "use".equals(machineProblems.getFit())){
        	machineProblems.setFit("使用");
        }else if (machineProblems.getFit()!=null && "nouse".equals(machineProblems.getFit())) {
        	machineProblems.setFit("没使用");
		}
        boolean b = machineProblemsService.insert(machineProblems);
        if (b) {
            return renderSuccess("添加成功！");
        } else {
            return renderError("添加失败！");
        }
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(String problemId) {
        boolean b = machineProblemsService.deleteById(problemId);
        if (b) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
    
    /**
     * 编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, String problemId) {
        MachineProblems machineProblems = machineProblemsService.selectById(problemId);
        model.addAttribute("machineProblems", machineProblems);
        return "admin/machineProblems/machineProblemsEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid MachineProblems machineProblems) {
    	if (machineProblems.getQuestionStatus()!=null && "已解决".equals(machineProblems.getQuestionStatus()) && machineProblems.getEndTime()==null) {
			machineProblems.setEndTime(new Date());
		}
        boolean b = machineProblemsService.updateById(machineProblems);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
