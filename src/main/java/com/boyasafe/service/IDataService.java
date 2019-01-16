package com.boyasafe.service;
import com.boyasafe.commons.result.PageInfo;


public interface IDataService{
	void selectDataGridByProvince(PageInfo pageInfo);
	void selectDataGridByPrinciple(PageInfo pageInfo);
}
