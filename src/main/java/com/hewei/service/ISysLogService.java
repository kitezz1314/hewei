package com.hewei.service;

import com.baomidou.mybatisplus.service.IService;
import com.hewei.commons.result.PageInfo;
import com.hewei.model.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends IService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);

}