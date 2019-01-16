package com.hewei.service;

import com.hewei.commons.result.PageInfo;
import com.hewei.model.Clean;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-23
 */
public interface ICleanService extends IService<Clean> {
	 void selectDataGrid(PageInfo pageInfo);
}
