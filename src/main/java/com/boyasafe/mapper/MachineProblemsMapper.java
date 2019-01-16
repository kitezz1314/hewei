package com.boyasafe.mapper;

import java.util.List;
import java.util.Map;

import com.boyasafe.model.MachineProblems;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-22
 */
public interface MachineProblemsMapper extends BaseMapper<MachineProblems> {
	 List<Map<String, Object>> selectMachineProblemsPage(Pagination page, Map<String, Object> params);
}