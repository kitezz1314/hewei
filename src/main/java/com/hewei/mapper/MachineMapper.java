package com.hewei.mapper;

import java.util.List;
import java.util.Map;

import com.hewei.model.Machine;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author chenzhuo
 * @since 2017-10-20
 */
public interface MachineMapper extends BaseMapper<Machine> {
	 List<Map<String, Object>> selectMachinePage(Pagination page, Map<String, Object> params);
	 boolean deleteByCode(String code);
	 Machine seleceByCode(String code);
	 boolean updateByCode(Machine machine);
	 Machine seleceByNote(String note);
}