package com.hewei.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hewei.commons.result.PageInfo;
import com.hewei.model.City;
import com.hewei.model.Machine;
import com.hewei.model.MachineInfo;
import com.hewei.model.vo.UserVo;
import com.hewei.service.CityService;
import com.hewei.service.IMachineInfoService;
import com.hewei.service.IMachineService;
import com.hewei.service.IUserService;
import com.hewei.service.MachineCodeService;
import com.hewei.commons.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-22
 */
@Controller
@RequestMapping("/machineInfo")
public class MachineInfoController extends BaseController {

    @Autowired 
    private IMachineInfoService machineInfoService;
    @Autowired
    private IUserService userService;
    @Autowired
    private MachineCodeService machineCodeService;
    @Autowired
    private IMachineService machineService;
    @Autowired
    private CityService CityService;
    @GetMapping("/manager")
    public String manager() {
        return "admin/machineInfo/machineInfoList";
    }
    
    @PostMapping("/dataGrid")
    @ResponseBody
    public PageInfo dataGrid(MachineInfo machineInfo, Integer page, Integer rows, String sort,String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        UserVo userVo = userService.selectVoById(this.getUserId());
        if (machineInfo.getMachineId() != null && !"".equals(machineInfo.getMachineId())) {
            condition.put("machineId", machineInfo.getMachineId());
        }
        if (machineInfo.getPrinciple() != null && !"".equals(machineInfo.getPrinciple())) {
            condition.put("principle", machineInfo.getPrinciple());
        }
        if (machineInfo.getNetName() != null && !"".equals(machineInfo.getNetName())) {
            condition.put("netName", machineInfo.getNetName());
        }
        condition.put("organizationId", userVo.getOrganizationId());
       
        pageInfo.setCondition(condition);
        machineInfoService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    /**
     * 添加页面
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/machineInfo/machineInfoAdd";
    }
    
    /**
     * 添加
     * @param 
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid MachineInfo machineInfo) {
        int b = machineInfoService.insertSelective(machineInfo);
        if (b>0) {
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
    public Object delete(String machineId) {
        boolean b = machineInfoService.deleteById(machineId);
        if (b) {
            return renderSuccess("删除成功！");
        } else {
            return renderError("删除失败！");
        }
    }
    
    /**
     * 编辑页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, String machineId) {
        MachineInfo machineInfo = machineInfoService.selectMachineById(machineId);
        model.addAttribute("machineInfo", machineInfo);
        return "admin/machineInfo/machineInfoEdit";
    }
    
    /**
     * 编辑
     * @param 
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid MachineInfo machineInfo) {
    	Machine machineType = machineService.selectByNote(machineInfo.getMachineType());
    	if (machineType!=null) {
    		machineInfo.setMachineType(machineType.getCode());
		}
    	Machine machineNumber = machineService.selectByNote(machineInfo.getMachineNumber());
    	if (machineNumber!=null) {
    		machineInfo.setMachineNumber(machineNumber.getCode());
		}
    	City province = CityService.selectByNote(machineInfo.getProvince());
    	if (province != null) {
			machineInfo.setProvince(province.getCode());
		}
    	City city = CityService.selectByNote(machineInfo.getCity());
    	if (city != null) {
			machineInfo.setCity(city.getCode());
		}
    	City county = CityService.selectByNote(machineInfo.getCounty());
    	if (county != null) {
			machineInfo.setCounty(county.getCode());
		}
        boolean b = machineInfoService.updateById(machineInfo);
        if (b) {
            return renderSuccess("编辑成功！");
        } else {
            return renderError("编辑失败！");
        }
    }
    
    /**
     * 模板下载
     * @param 
     * @param id
     * @return
     */
    @GetMapping("/download")
    public String download(HttpServletRequest request, HttpServletResponse response) {
    	 response.setCharacterEncoding("utf-8");
         response.setContentType("multipart/form-data");
         response.setHeader("Content-Disposition", "attachment;fileName="+ "muban.xlsx");
         try {
             String path = Thread.currentThread().getContextClassLoader()
                     .getResource("").getPath()
                     + "download";//这个download目录为啥建立在classes下的
            System.out.println(path);
             
             InputStream inputStream = new FileInputStream(new File(path+ File.separator + "muban.xlsx"));

             OutputStream os = response.getOutputStream();
             byte[] b = new byte[2048];
             int length;
             while ((length = inputStream.read(b)) > 0) {
                 os.write(b, 0, length);
             }
              // 这里主要关闭。
             os.close();
             inputStream.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
             //  返回值要注意，要不然就出现下面这句错误！
             //java+getOutputStream() has already been called for this response
         return null;
     
    }
    
    @RequestMapping("/upload")
    public Object upload(MultipartHttpServletRequest request) {
    	 MultipartFile file = request.getFile("fileimport");
    	 System.out.println(file);
    	 return renderSuccess("上传成功 ");
    }
}
