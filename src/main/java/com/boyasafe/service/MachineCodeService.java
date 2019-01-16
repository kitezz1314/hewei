package com.boyasafe.service;

import java.util.List;

public interface MachineCodeService {
	//��ѯʡ  
		public List findType(String code) throws Exception;  
		  
		//��ѯ��  
		public List findNumber(String code) throws Exception;
}
