package com.hewei.service.impl;

import java.util.List;
import java.util.Map;

import com.hewei.commons.result.PageInfo;
import com.hewei.model.Clean;
import com.hewei.mapper.CleanMapper;
import com.hewei.service.ICleanService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-23
 */
@Service
public class CleanServiceImpl extends ServiceImpl<CleanMapper, Clean> implements ICleanService {
	@Autowired
	private CleanMapper cleanMapper;
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
        page.setOrderByField(pageInfo.getSort());
        page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
        List<Map<String, Object>> list = cleanMapper.selectCleanPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}
	
}
