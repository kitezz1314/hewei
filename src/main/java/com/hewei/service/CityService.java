package com.hewei.service;

import java.util.List;

import com.hewei.model.City;

public interface CityService {
	//��ѯʡ  
	public List findProvince(String code) throws Exception;  
	  
	//��ѯ��  
	public List findCity(String code) throws Exception;  
	
	//��ѯ����  
	public List findCounty(String code) throws Exception; 
	
	public City selectByNote(String note);

}
