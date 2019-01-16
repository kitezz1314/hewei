package com.boyasafe.service;

import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.model.Malfunction;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenzhuo
 * @since 2018-06-19
 */
public interface IMalfunctionService extends IService<Malfunction> {
	 void selectDataGrid(PageInfo pageInfo);
	 int insertReturnId(Malfunction malfunction);
	 int countByWechatUserId(String by1);
	 
	 Map<String, Object> selectProblemView(String openid);
	 
	 List<Malfunction> selectProblemsList(String openid);
	 
	 boolean updateStatus(String id);
	 
}
