package com.boyasafe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.mapper.DataMapper;

import com.boyasafe.service.IDataService;

@Service
public class DataServiceImpl implements IDataService{
	@Autowired
	private DataMapper dataMapper;
	@Override
	public void selectDataGridByProvince(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		 Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
	     page.setOrderByField(pageInfo.getSort());
	     page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
	     List<Map<String, Object>> list = dataMapper.selectDataPageByProvince(page, pageInfo.getCondition());
	     pageInfo.setRows(list);
	     pageInfo.setTotal(page.getTotal());
	}
	@Override
	public void selectDataGridByPrinciple(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		 Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
	     page.setOrderByField(pageInfo.getSort());
	     page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
	     List<Map<String, Object>> list = dataMapper.selectDataPageByPrinciple(page, pageInfo.getCondition());
	     pageInfo.setRows(list);
	     pageInfo.setTotal(page.getTotal());
	}

}
