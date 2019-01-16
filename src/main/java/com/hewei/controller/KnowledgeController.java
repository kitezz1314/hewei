package com.hewei.controller;

import javax.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hewei.commons.result.PageInfo;
import com.hewei.model.Knowledge;
import com.hewei.service.IKnowledgeService;
import com.hewei.commons.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-23
 */
@Controller
@RequestMapping("/knowledge")
public class KnowledgeController extends BaseController {

    @Autowired private IKnowledgeService knowledgeService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/knowledge/knowledgeList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Knowledge knowledge, Integer page, Integer rows, String sort,String order) {
    	PageInfo pageInfo = new PageInfo(page, rows, sort, order);
    	if (knowledge.getKnowledgeName()==null || "".equals(knowledge.getKnowledgeName())) {
			return pageInfo;
		}
    	else{
    		Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("knowledgeName", knowledge.getKnowledgeName());
            pageInfo.setCondition(condition);
            knowledgeService.selectDataGrid(pageInfo);
            return pageInfo;
    	}
        
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/knowledge/knowledgeAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Knowledge knowledge) {
       knowledge.setCreateTime(new Date());
       knowledge.setUserName(this.getShiroUser().getName());
        boolean b = knowledgeService.insert(knowledge);
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
    public Object delete(Long id) {
      
        boolean b = knowledgeService.deleteById(id);
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
    public String editPage(Model model, Long knowledgeId) {
        Knowledge knowledge = knowledgeService.selectById(knowledgeId);
        model.addAttribute("knowledge", knowledge);
        return "admin/knowledge/knowledgeEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Knowledge knowledge) {
        boolean b = knowledgeService.updateById(knowledge);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
