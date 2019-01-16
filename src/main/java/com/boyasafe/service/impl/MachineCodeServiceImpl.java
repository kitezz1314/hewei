package com.boyasafe.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boyasafe.mapper.MachineCodeMapper;
import com.boyasafe.service.MachineCodeService;


@Service
public class MachineCodeServiceImpl implements MachineCodeService{
	@Resource
	private MachineCodeMapper machineCodeMapper;

	@Override
	public List findType(String code) throws Exception {
		// TODO Auto-generated method stub
		code="00";
		return machineCodeMapper.findType(code);
	}

	@Override
	public List findNumber(String code) throws Exception {
		// TODO Auto-generated method stub
		return machineCodeMapper.findNumber(code);
	}
}
