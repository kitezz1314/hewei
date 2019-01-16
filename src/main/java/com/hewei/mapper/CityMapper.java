package com.hewei.mapper;

import java.util.List;

import com.hewei.model.City;



public interface CityMapper {
    int deleteByPrimaryKey(String code);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(String code);
    
    City selectByNote(String note);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    
  
  	public List findProvince(String code) throws Exception;  
  	  
  	
  	public List findCity(String code) throws Exception;  
  	  
  	 
  	public List findCounty(String code) throws Exception;
}