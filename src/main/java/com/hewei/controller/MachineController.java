package com.hewei.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hewei.commons.result.PageInfo;
import com.hewei.model.Machine;
import com.hewei.service.IMachineService;
import com.hewei.commons.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-20
 */
@Controller
@RequestMapping("/machine")
public class MachineController extends BaseController {

    @Autowired 
    private IMachineService machineService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/machine/machineList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Machine machine, Integer page, Integer rows, String sort,String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        machineService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/machine/machineAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Machine machine) {
        boolean b = machineService.insert(machine);
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
    public Object delete(String code) {
        boolean b = machineService.deleteByCode(code);
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
    public String editPage(Model model, String code) {
        Machine machine = machineService.selectByCode(code);
        model.addAttribute("machine", machine);
        return "admin/machine/machineEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Machine machine) {
        boolean b = machineService.updateByCode (machine);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
