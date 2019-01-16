package com.boyasafe.mapper;

import com.boyasafe.model.Malfunction;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author chenzhuo
 * @since 2018-06-19
 */
public interface MalfunctionMapper extends BaseMapper<Malfunction> {
	 List<Map<String, Object>> selectMalfunctionPage(Pagination page, Map<String, Object> params);
	 int insertReturnId(Malfunction malfunction);
	 int countByWechatUserId(String by1);
	 Map<String, Object> selectProblemView(String openid);
	 List<Malfunction> selectProblemsList(String openid);
	 boolean updateStatus(String id);
}