package com.boyasafe.service.impl;

import java.util.List;
import java.util.Map;

import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.model.MachineInfo;
import com.boyasafe.mapper.MachineInfoMapper;
import com.boyasafe.service.IMachineInfoService;
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
 * @since 2017-10-22
 */
@Service
public class MachineInfoServiceImpl extends ServiceImpl<MachineInfoMapper, MachineInfo> implements IMachineInfoService {
	@Autowired
	private MachineInfoMapper machineInfoMapper;
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		 Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
	        page.setOrderByField(pageInfo.getSort());
	        page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
	        List<Map<String, Object>> list = machineInfoMapper.selectMachineInfoPage(page, pageInfo.getCondition());
	        pageInfo.setRows(list);
	        pageInfo.setTotal(page.getTotal());
	}
	@Override
	public int insertSelective(MachineInfo record) {
		// TODO Auto-generated method stub
		return machineInfoMapper.insertSelective(record);
	}
	@Override
	public MachineInfo selectMachineById(String id) {
		// TODO Auto-generated method stub
		return machineInfoMapper.selectMachineById(id);
	}
}
