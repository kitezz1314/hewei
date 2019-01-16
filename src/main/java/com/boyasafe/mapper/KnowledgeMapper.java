package com.boyasafe.mapper;

import java.util.List;
import java.util.Map;

import com.boyasafe.model.Knowledge;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-23
 */
public interface KnowledgeMapper extends BaseMapper<Knowledge> {
	 List<Map<String, Object>> selectKnowledgePage(Pagination page, Map<String, Object> params);
}