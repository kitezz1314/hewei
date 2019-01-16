package com.hewei.service.impl;

import java.util.List;
import java.util.Map;

import com.hewei.commons.result.PageInfo;
import com.hewei.model.Machine;
import com.hewei.mapper.MachineMapper;
import com.hewei.service.IMachineService;
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
 * @since 2017-10-20
 */
@Service
public class MachineServiceImpl extends ServiceImpl<MachineMapper, Machine> implements IMachineService {
	@Autowired
	private MachineMapper machineMapper;
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		 Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
	        page.setOrderByField(pageInfo.getSort());
	        page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
	        List<Map<String, Object>> list = machineMapper.selectMachinePage(page, pageInfo.getCondition());
	        pageInfo.setRows(list);
	        pageInfo.setTotal(page.getTotal());
	}
	@Override
	public boolean deleteByCode(String code) {
		// TODO Auto-generated method stub
		return machineMapper.deleteByCode(code);
	}
	@Override
	public Machine selectByCode(String code) {
		// TODO Auto-generated method stub
		return machineMapper.seleceByCode(code);
	}
	@Override
	public boolean updateByCode(Machine machine) {
		// TODO Auto-generated method stub
		return machineMapper.updateByCode(machine);
	}
	@Override
	public Machine selectByNote(String note) {
		// TODO Auto-generated method stub
		return machineMapper.seleceByNote(note);
	}
	
}
