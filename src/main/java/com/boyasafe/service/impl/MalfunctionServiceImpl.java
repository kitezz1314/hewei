package com.boyasafe.service.impl;

import com.boyasafe.model.Malfunction;
import com.boyasafe.commons.result.PageInfo;
import com.boyasafe.mapper.MalfunctionMapper;
import com.boyasafe.service.IMalfunctionService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenzhuo
 * @since 2018-06-19
 */
@Service
public class MalfunctionServiceImpl extends ServiceImpl<MalfunctionMapper, Malfunction> implements IMalfunctionService {
	@Autowired
	private MalfunctionMapper malfunctionMapper;
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		 Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
	        page.setOrderByField(pageInfo.getSort());
	        page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
	        List<Map<String, Object>> list = malfunctionMapper.selectMalfunctionPage(page, pageInfo.getCondition());
	        pageInfo.setRows(list);
	        pageInfo.setTotal(page.getTotal());
	}
	@Override
	public int insertReturnId(Malfunction malfunction) {
		// TODO Auto-generated method stub
		return malfunctionMapper.insertReturnId(malfunction);
	}
	@Override
	public int countByWechatUserId(String by1) {
		// TODO Auto-generated method stub
		return malfunctionMapper.countByWechatUserId(by1);
	}
	@Override
	public Map<String, Object> selectProblemView(String openid) {
		// TODO Auto-generated method stub
		return malfunctionMapper.selectProblemView(openid);
	}
	@Override
	public List<Malfunction> selectProblemsList(String openid) {
		// TODO Auto-generated method stub
		return malfunctionMapper.selectProblemsList(openid);
	}
	@Override
	public boolean updateStatus(String id) {
		// TODO Auto-generated method stub
		return malfunctionMapper.updateStatus(id);
	}
	
	
}
