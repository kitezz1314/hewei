package com.boyasafe.service;

import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.model.MachineProblems;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-22
 */
public interface IMachineProblemsService extends IService<MachineProblems> {
	 void selectDataGrid(PageInfo pageInfo);
}
