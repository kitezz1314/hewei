package com.hewei.service;

import com.hewei.commons.result.PageInfo;
import com.hewei.model.MachineInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-22
 */
public interface IMachineInfoService extends IService<MachineInfo> {
	 void selectDataGrid(PageInfo pageInfo);
	 int insertSelective(MachineInfo record);
	 MachineInfo selectMachineById(String id);
}
