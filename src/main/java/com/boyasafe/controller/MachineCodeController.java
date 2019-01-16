package com.boyasafe.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



import com.boyasafe.commons.utils.JsonConvert;
import com.boyasafe.service.MachineCodeService;

@Controller
@RequestMapping("/machinecode")
public class MachineCodeController {
	@Resource
	private MachineCodeService machineCodeService;
	//��ѯȫ�����������ʡ  
	@RequestMapping("/type")
	public String provinceCombobox(HttpServletResponse response,HttpServletRequest request) throws Exception{  
	      
	    List list=machineCodeService.findType("00");
	    this.jsonUtil(list,response);  
	    return null;  
	}  
	  
	//��ѯȫ�������������  
	@RequestMapping("/number")
	public String cityCombobox(HttpServletResponse response,HttpServletRequest request) throws Exception{  
	          
	    List list=machineCodeService.findNumber(request.getParameter("type"));  
	    this.jsonUtil(list,response);  
	    return null;  
	}  
	        
	//����json���߷������������alist  
	public void jsonUtil(Object accountlist,HttpServletResponse response) throws Exception{  
	           
	        String returnJson = JsonConvert.returnJson(accountlist);  
	        response.setCharacterEncoding("utf-8");  
	        response.getWriter().println(returnJson);  
	    }  
}
