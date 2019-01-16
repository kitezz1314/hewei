package com.hewei.service;

import com.hewei.commons.result.PageInfo;
import com.hewei.model.Machine;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-20
 */
public interface IMachineService extends IService<Machine> {
	//查询所有机器做分页
	void selectDataGrid(PageInfo pageInfo);
	//根据机器编号删除
	boolean deleteByCode(String code);
	//根据机编号获取机器
	Machine selectByCode(String code);
	//根据机编号获取机器
	Machine selectByNote(String note); 
	boolean updateByCode(Machine machine);
}
