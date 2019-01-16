package com.boyasafe.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.boyasafe.mapper.CityMapper;
import com.boyasafe.model.City;
import com.boyasafe.service.CityService;


@Service
public class CityServiceImpl implements CityService{
	@Resource
	private CityMapper cityMapper;
	@Override
	public List findProvince(String code) throws Exception {
		// TODO Auto-generated method stub
		code="0000";
		return cityMapper.findProvince(code);
	}

	@Override
	public List findCity(String code) throws Exception {
		// TODO Auto-generated method stub
		return cityMapper.findCity(code);
	}

	@Override
	public List findCounty(String code) throws Exception {
		// TODO Auto-generated method stub
		return cityMapper.findCounty(code);
	}

	@Override
	public City selectByNote(String note) {
		// TODO Auto-generated method stub
		return cityMapper.selectByNote(note);
	}

}
