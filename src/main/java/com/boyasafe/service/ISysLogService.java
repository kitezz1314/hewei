package com.boyasafe.service;

import com.baomidou.mybatisplus.service.IService;
import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.model.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends IService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);

}