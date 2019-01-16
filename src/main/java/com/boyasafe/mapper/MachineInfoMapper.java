package com.boyasafe.mapper;

import java.util.List;
import java.util.Map;

import com.boyasafe.model.MachineInfo;
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
public interface MachineInfoMapper extends BaseMapper<MachineInfo> {
	 List<Map<String, Object>> selectMachineInfoPage(Pagination page, Map<String, Object> params);
	 int insertSelective(MachineInfo record);
	 MachineInfo selectMachineById(String machineId);
	 boolean updateMachineById(MachineInfo machineInfo);
}