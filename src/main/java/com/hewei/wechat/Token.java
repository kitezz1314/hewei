package com.hewei.wechat;


import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.hewei.mapper.TokenMapper;
import com.hewei.mybatis.BaseMapperTest;

public class Token {
	public com.hewei.model.Token geToken() throws Exception{
		BaseMapperTest baseMapperTest = new BaseMapperTest();
		SqlSession sqlSession = baseMapperTest.getSqlSession();
		TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
		com.hewei.model.Token tokenlocal = tokenMapper.selectById(1l);
		Date currentTime = new Date();
		if ((currentTime.getTime()-tokenlocal.getExpiresIn()*1000)<tokenlocal.getCreatetime().getTime()) {
			return tokenlocal;
		}else{
			 String path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ tokenlocal.getAppid() + "&secret=" + tokenlocal.getAppsecret();
			 HttpClient httpClient = new HttpClient();
			 GetMethod getMethod = new GetMethod(path);
			 httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			 httpClient.executeMethod(getMethod);
			 // 读取内容
			 byte[] responseBody = getMethod.getResponseBody();
			 String strResp = new String(responseBody, "UTF-8");
			 Map<String, Object> map = (Map<String, Object>) JSON.parse(strResp);
			 tokenlocal.setCreatetime(new Date());
			 tokenlocal.setExpiresIn(Long.valueOf(map.get("expires_in").toString()));
			 tokenlocal.setToken(map.get("access_token").toString());
			 tokenMapper.updateById(tokenlocal);
			 sqlSession.commit();
			 sqlSession.close();
			 return tokenlocal;
		}
		
	}
}
