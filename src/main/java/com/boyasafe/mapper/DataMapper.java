package com.boyasafe.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;


public interface DataMapper{
	 //按省和项目负责人分组
	 List<Map<String, Object>> selectDataPageByProvince(Pagination page, Map<String, Object> params);
	 //按项目负责人分组
	 List<Map<String, Object>> selectDataPageByPrinciple(Pagination page, Map<String, Object> params);
}
