package com.hewei.service;
import com.hewei.commons.result.PageInfo;


public interface IDataService{
	void selectDataGridByProvince(PageInfo pageInfo);
	void selectDataGridByPrinciple(PageInfo pageInfo);
}
