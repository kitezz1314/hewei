package com.boyasafe.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.boyasafe.commons.base.BaseController;
import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.service.IDataService;

@Controller
@RequestMapping("/data")
public class DataController extends BaseController{
	@Autowired
	private IDataService dataService;
		@GetMapping("/manager")
	    public String manager() {
	        return "admin/data/dataList1";
	    }
	    
	    @PostMapping("/dataGrid")
	    @ResponseBody
	    public PageInfo dataGrid(Date createdateStart,Date createdateEnd, Integer page, Integer rows, String sort,String order) {
	        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	        Map<String, Object> condition = new HashMap<String, Object>();
	        if (createdateStart != null) {
	            condition.put("startTime", createdateStart);
	        }
	        if (createdateEnd != null) {
	            condition.put("endTime", createdateEnd);
	        }
	        pageInfo.setCondition(condition);
	        dataService.selectDataGridByProvince(pageInfo);
	        return pageInfo;
	    }
	    
	    @RequestMapping("/echarts")
	    @ResponseBody
	    public String echarts(Date createdateStart,Date createdateEnd, Integer page, Integer rows, String sort,String order) {
	        page = 1;
	        rows = 20;
	        sort = "number";
	        order = "asc";
	        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	        Map<String, Object> condition = new HashMap<String, Object>();
	        if (createdateStart != null) {
	            condition.put("startTime", createdateStart);
	        }
	        if (createdateEnd != null) {
	            condition.put("endTime", createdateEnd);
	        }
	        pageInfo.setCondition(condition); 
	        dataService.selectDataGridByProvince(pageInfo);
	        List<Map<String, Object>> list = pageInfo.getRows();
	        String categories[]=new String[list.size()];
	        int data[]=new int[list.size()];
	        for (int i = 0; i < list.size(); i++) {
				categories[i] = list.get(i).get("province").toString();
				data[i] = Integer.valueOf(list.get(i).get("number").toString());
			}
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("categories", categories);
	        map.put("data", data);
	        return JSON.toJSONString(map);
	    }
	    
	    
	    @GetMapping("/manager2")
	    public String manager2() {
	        return "admin/data/dataList2";
	    }
	    @PostMapping("/dataGrid2")
	    @ResponseBody
	    public PageInfo dataGrid2(Date createdateStart2,Date createdateEnd2, Integer page, Integer rows, String sort,String order) {
	        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	        Map<String, Object> condition = new HashMap<String, Object>();
	        if (createdateStart2 != null) {
	            condition.put("startTime", createdateStart2);
	        }
	        if (createdateEnd2 != null) {
	            condition.put("endTime", createdateEnd2);
	        }
	        pageInfo.setCondition(condition);
	        dataService.selectDataGridByPrinciple(pageInfo);
	        return pageInfo;
	    }
	    
	    @RequestMapping("/echarts2")
	    @ResponseBody
	    public String echarts2(Date createdateStart,Date createdateEnd, Integer page, Integer rows, String sort,String order) {
	        page = 1;
	        rows = 20;
	        sort = "number";
	        order = "asc";
	        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
	        Map<String, Object> condition = new HashMap<String, Object>();
	        if (createdateStart != null) {
	            condition.put("startTime", createdateStart);
	        }
	        if (createdateEnd != null) {
	            condition.put("endTime", createdateEnd);
	        }
	        pageInfo.setCondition(condition); 
	        dataService.selectDataGridByPrinciple(pageInfo);
	        List<Map<String, Object>> list = pageInfo.getRows();
	        String categories[]=new String[list.size()];
	        int data[]=new int[list.size()];
	        for (int i = 0; i < list.size(); i++) {
				categories[i] = list.get(i).get("principle").toString();
				data[i] = Integer.valueOf(list.get(i).get("number").toString());
			}
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("categories", categories);
	        map.put("data", data);
	        return JSON.toJSONString(map);
	    }
}
