package com.hewei.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hewei.commons.utils.JsonConvert;
import com.hewei.service.CityService;


@Controller
@RequestMapping("/city")
public class CityController {
	@Resource
	private CityService cityService;
	
	@RequestMapping("/province")
	public String provinceCombobox(HttpServletResponse response,HttpServletRequest request) throws Exception{  
	      
	    List list=cityService.findProvince("0000");
	    this.jsonUtil(list,response);  
	    return null;  
	}  
	  
	
	@RequestMapping("/city")
	public String cityCombobox(HttpServletResponse response,HttpServletRequest request) throws Exception{  
	          
	    List list=cityService.findCity(request.getParameter("province"));  
	    this.jsonUtil(list,response);  
	    return null;  
	}  
	      
	
	@RequestMapping("/county")
	public String countyCombobox(HttpServletResponse response,HttpServletRequest request) throws Exception{  
	      
	    List list=cityService.findCounty(request.getParameter("city"));  
	    this.jsonUtil(list,response);  
	    return null;  
	}  
	  
	  
	
	public void jsonUtil(Object accountlist,HttpServletResponse response) throws Exception{  
	           
	        String returnJson = JsonConvert.returnJson(accountlist);  
	        response.setCharacterEncoding("utf-8");  
	        response.getWriter().println(returnJson);  
	    }  

}
