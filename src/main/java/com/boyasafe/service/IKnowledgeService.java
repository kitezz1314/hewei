package com.boyasafe.service;

import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.model.Knowledge;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-23
 */
public interface IKnowledgeService extends IService<Knowledge> {
	void selectDataGrid(PageInfo pageInfo);
}
