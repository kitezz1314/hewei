package com.boyasafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.model.Clean;
import com.boyasafe.service.ICleanService;
import com.boyasafe.commons.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-23
 */
@Controller
@RequestMapping("/clean")
public class CleanController extends BaseController {

    @Autowired private ICleanService cleanService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/clean/cleanList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Clean clean, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        if (clean.getMachineId() != null && !"".equals(clean.getMachineId())) {
            condition.put("machineId", clean.getMachineId());
        }
        if (clean.getCleaner() != null && !"".equals(clean.getCleaner())) {
            condition.put("cleaner", clean.getCleaner());
        }
        if (clean.getBy1() != null && !"".equals(clean.getBy1())) {
            condition.put("by1", clean.getBy1());
        }
        pageInfo.setCondition(condition);
        cleanService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model, String machineId) {
    	model.addAttribute("machineId", machineId);
        return "admin/machineInfo/cleanAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Clean clean) {
        boolean b = cleanService.insert(clean);
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
    public Object delete(Long cleanId) {
        boolean b = cleanService.deleteById(cleanId);
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
    public String editPage(Model model, Long cleanId) {
        Clean clean = cleanService.selectById(cleanId);
        model.addAttribute("clean", clean);
        return "admin/clean/cleanEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Clean clean) {
        boolean b = cleanService.updateById(clean);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
