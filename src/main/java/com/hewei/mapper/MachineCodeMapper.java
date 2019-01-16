package com.hewei.mapper;

import java.util.List;

import com.hewei.model.Machine;



public interface MachineCodeMapper {
    int deleteByPrimaryKey(String code);

    int insert(Machine record);

    int insertSelective(Machine record);

    Machine selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);
    
  //��ѯʡ  
  	public List findType(String code) throws Exception;  
  	  
  	//��ѯ��  
  	public List findNumber(String code) throws Exception;  
}