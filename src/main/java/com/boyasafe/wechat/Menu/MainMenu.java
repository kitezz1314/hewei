package com.boyasafe.wechat.Menu;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alibaba.fastjson.JSONObject;
import com.boyasafe.wechat.Token;

public class MainMenu {
	/**
	 * 创建菜单
	 * 
	 * @param menu
	 * @return
	 */
	public boolean createMenu() throws Exception{

		// 1、获取access_token
		Token token = new Token();
		String tAccess_Token = token.geToken().getToken();

		// 2、组建菜单
		String tMenuJSON = JSONObject.toJSONString(getMenu());

		// 3、请求调用
		String result = createMenubyHttps(tAccess_Token, tMenuJSON);

		System.out.println(result);

		return true;
	}

	/**
	 * 定义菜单属性
	 * 
	 * @return
	 */
	private Menu getMenu() {
		Menu menu = new Menu();

		// 建3个导航菜单
		LevelMenu tLevelMenuOne = new LevelMenu();
		tLevelMenuOne.setName("客户报障");
		LevelMenu tLevelMenuTwo = new LevelMenu();
		tLevelMenuTwo.setName("信息查询");
		LevelMenu tLevelMenuThree = new LevelMenu();
		tLevelMenuThree.setName("更多操作");

		// 第一个导航菜单的子菜单
		SubMenuButton tSubMenuButton_oneone = new SubMenuButton();
		tSubMenuButton_oneone.setType("view");
		tSubMenuButton_oneone.setName("自助报障");
		tSubMenuButton_oneone.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx17717cc913f8ddc7&redirect_uri=http://aftersale.boyasafe.com/wechat/addPage&response_type=code&scope=snsapi_base&state=123#wechat_redirect");

		SubMenuButton tSubMenuButton_onetwo = new SubMenuButton();
		tSubMenuButton_onetwo.setType("click");
		tSubMenuButton_onetwo.setName("电话报障");
		tSubMenuButton_onetwo.setKey("12");
		
		SubMenuButton tSubMenuButton_onethree = new SubMenuButton();
		tSubMenuButton_onethree.setType("view");
		tSubMenuButton_onethree.setName("我的报障");
		tSubMenuButton_onethree.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx17717cc913f8ddc7&redirect_uri=http://aftersale.boyasafe.com/wechat/problemPreview&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
		
		// 加入导航菜单
		tLevelMenuOne.setSub_button(new SubMenuButton[] { tSubMenuButton_oneone, tSubMenuButton_onetwo, tSubMenuButton_onethree });

		// 第二 个导航菜单的子菜单
		SubMenuButton tSubMenuButton_twoone = new SubMenuButton();
		tSubMenuButton_twoone.setType("click");
		tSubMenuButton_twoone.setName("BY-W3信息");
		tSubMenuButton_twoone.setKey("21");

		SubMenuButton tSubMenuButton_twotwo = new SubMenuButton();
		tSubMenuButton_twotwo.setType("click");
		tSubMenuButton_twotwo.setName("Avansia信息");
		tSubMenuButton_twotwo.setKey("22");

		SubMenuButton tSubMenuButton_twothree = new SubMenuButton();
		tSubMenuButton_twothree.setType("view");
		tSubMenuButton_twothree.setName("我的故障单");
		tSubMenuButton_twothree.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx17717cc913f8ddc7&redirect_uri=http://aftersale.boyasafe.com/wechat/problemsList&response_type=code&scope=snsapi_base&state=123#wechat_redirect");

		// 加入导航菜单
		tLevelMenuTwo.setSub_button(
				new SubMenuButton[] { tSubMenuButton_twoone, tSubMenuButton_twotwo, tSubMenuButton_twothree });

		// 第三个导航菜单的子菜单
		SubMenuButton tSubMenuButton_threeone = new SubMenuButton();
		tSubMenuButton_threeone.setType("click");
		tSubMenuButton_threeone.setName("投诉建议");
		tSubMenuButton_threeone.setKey("31");

		SubMenuButton tSubMenuButton_threetwo = new SubMenuButton();
		tSubMenuButton_threetwo.setType("click");
		tSubMenuButton_threetwo.setName("服务评价");
		tSubMenuButton_threetwo.setKey("32");
		
		SubMenuButton tSubMenuButton_threethree = new SubMenuButton();
		tSubMenuButton_threethree.setType("view");
		tSubMenuButton_threethree.setName("邮箱登录");
		tSubMenuButton_threethree.setUrl("http://mail.boyasafe.com/");
		
		SubMenuButton tSubMenuButton_threefour = new SubMenuButton();
		tSubMenuButton_threefour.setType("view");
		tSubMenuButton_threefour.setName("售后系统");
		tSubMenuButton_threefour.setUrl("http://aftersale.boyasafe.com/login");
		// 加入导航菜单
		tLevelMenuThree.setSub_button(new SubMenuButton[] { tSubMenuButton_threeone, tSubMenuButton_threetwo, tSubMenuButton_threethree, tSubMenuButton_threefour});

		menu.setButton(new MenuButton[] { tLevelMenuOne, tLevelMenuTwo, tLevelMenuThree });

		return menu;

	}

	/**
	 * 请求调用，设置菜单信息
	 * 
	 * @param url
	 * @param requestData
	 * @return
	 */
	private String createMenubyHttps(String access_token, String requestData) {
		// 返回报文
		String strResp = "";
		String path = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
		try {
			  HttpClient httpClient = new HttpClient();
		        PostMethod postMethod = new PostMethod(path);
		        // 设置编码
		        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		        
		        System.out.println("path:" + path);
		        System.out.println("requestData:" + requestData);

		        postMethod.setRequestBody(requestData);

		        long start = System.currentTimeMillis();
		        // 执行getMethod
		        int statusCode = httpClient.executeMethod(postMethod);
		        System.out.println("cost:" + (System.currentTimeMillis() - start));
		        // 失败
		        if (statusCode != HttpStatus.SC_OK)
		        {
		            System.out.println("Method failed: " + postMethod.getStatusLine());
		            // 读取内容
		            byte[] responseBody = postMethod.getResponseBody();
		            // 处理内容
		            strResp = new String(responseBody, "UTF-8");
		            System.out.println(strResp);
		        }
		        else
		        {
		            // 读取内容
		            byte[] responseBody = postMethod.getResponseBody();
		            strResp = new String(responseBody, "UTF-8");
		            System.out.println("服务器返回:" + strResp);
		        }

		        postMethod.releaseConnection();

		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!" + e);
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
		} catch (Exception e) {
			System.out.println(e);
		} finally {
		}
		return strResp;

	}
}
