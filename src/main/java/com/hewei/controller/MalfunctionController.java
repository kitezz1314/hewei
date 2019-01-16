package com.hewei.controller;

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
import com.hewei.commons.result.PageInfo;
import com.hewei.model.Malfunction;
import com.hewei.model.User;
import com.hewei.model.vo.UserVo;
import com.hewei.service.IMalfunctionService;
import com.hewei.service.IUserService;
import com.hewei.commons.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenzhuo
 * @since 2018-06-19
 */
@Controller
@RequestMapping("/malfunction")
public class MalfunctionController extends BaseController {

    @Autowired 
    private IMalfunctionService malfunctionService;
    @Autowired
    private IUserService userService;
    
    @GetMapping("/manager")
    public String manager() {
        return "admin/malfunction/malfunctionList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(Malfunction malfunction, Integer page, Integer rows, String sort,String order) {
    	logger.info("=================  test  ======================");
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        UserVo userVo = userService.selectVoById(this.getUserId());
        condition.put("organizationId", userVo.getOrganizationId());
        pageInfo.setCondition(condition);
        malfunctionService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/malfunction/malfunctionAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Malfunction malfunction) {
       
        boolean b = malfunctionService.insert(malfunction);
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
        Malfunction malfunction = new Malfunction();
       
        boolean b = malfunctionService.updateById(malfunction);
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
    public String editPage(Model model, Long id) {
        Malfunction malfunction = malfunctionService.selectById(id);
        model.addAttribute("malfunction", malfunction);
        return "admin/malfunction/malfunctionEdit";
    }
    
    
    
    
   
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Malfunction malfunction) {
    	User user = userService.selectUserByLoginName(malfunction.getBy2());
    	malfunction.setBy2(user.getAge());
    	malfunction.setStatus("1");
        boolean b = malfunctionService.updateById(malfunction);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
}
