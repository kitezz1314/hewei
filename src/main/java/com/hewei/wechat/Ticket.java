package com.hewei.wechat;

import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.hewei.mapper.TicketMapper;
import com.hewei.mybatis.BaseMapperTest;

public class Ticket {
	public com.hewei.model.Ticket getTicket() throws Exception{
		BaseMapperTest baseMapperTest = new BaseMapperTest();
		SqlSession sqlSession = baseMapperTest.getSqlSession();
		TicketMapper ticketMapper = sqlSession.getMapper(TicketMapper.class);
		com.hewei.model.Ticket ticketlocal = ticketMapper.selectById(1l);
		Date currentTime = new Date();
		if ((currentTime.getTime()-ticketlocal.getExpiresIn()*1000)<ticketlocal.getCreatetime().getTime()) {
			return ticketlocal;
		}else{
			Token token = new Token();
			com.hewei.model.Token tokenlocal = token.geToken();
			 String path = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+tokenlocal.getToken()+"&type=jsapi";
			 HttpClient httpClient = new HttpClient();
			 GetMethod getMethod = new GetMethod(path);
			 httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			 httpClient.executeMethod(getMethod);
			 // 读取内容
			 byte[] responseBody = getMethod.getResponseBody();
			 String strResp = new String(responseBody, "UTF-8");
			 Map<String, Object> map = (Map<String, Object>) JSON.parse(strResp);
			 ticketlocal.setCreatetime(new Date());
			 ticketlocal.setExpiresIn(Long.valueOf(map.get("expires_in").toString()));
			 ticketlocal.setTicket(map.get("ticket").toString());
			 ticketMapper.updateById(ticketlocal);
			 sqlSession.commit();
			 sqlSession.close();
			 return ticketlocal;
		}
		
	}
}
