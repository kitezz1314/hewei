package com.hewei.wechat;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hewei.commons.base.BaseController;
import com.hewei.model.MachineInfo;
import com.hewei.model.Malfunction;
import com.hewei.model.Token;
import com.hewei.service.IMachineInfoService;
import com.hewei.service.IMalfunctionService;

@Controller
@RequestMapping("wechat")
public class WechatController extends BaseController {
	protected Logger logger = LogManager.getLogger(getClass());
	public static final String TOKEN = "hewei";
	@Autowired
	private IMachineInfoService machineInfoService;
	@Autowired
	private IMalfunctionService malfunctionService;

	/**
	 * 微信接入
	 * 
	 * @param wc
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/connect", method = RequestMethod.GET)
	@ResponseBody
	public void connectWeixin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8"); // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
		response.setCharacterEncoding("UTF-8"); // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；boolean
												// isGet =
												// request.getMethod().toLowerCase().equals("get");
		PrintWriter out = response.getWriter();
		try {
			String signature = request.getParameter("signature");// 微信加密签名
			String timestamp = request.getParameter("timestamp");// 时间戳
			String nonce = request.getParameter("nonce");// 随机数
			String echostr = request.getParameter("echostr");// 随机字符串
			System.out.println(signature + timestamp + nonce + echostr);
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignUtil.checkSignature(TOKEN, signature, timestamp, nonce)) {
				logger.info("Connect the weixin server is successful.");
				response.getWriter().write(echostr);
			} else {
				logger.error("Failed to verify the signature!");
			}
		} catch (Exception e) {
			logger.error("Connect the weixin server is error.");
		} finally {
			out.close();
		}

	}

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	@ResponseBody
	public String weixinPost(HttpServletRequest request) {
		String respMessage = null;
		try {

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.xmlToMap(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息内容
			String content = requestMap.get("Content");

			logger.info(
					"FromUserName is:" + fromUserName + ", ToUserName is:" + toUserName + ", MsgType is:" + msgType);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 自动回复
				TextMessage text = new TextMessage();
				text.setContent(content);
				text.setToUserName(fromUserName);
				text.setFromUserName(toUserName);
				text.setCreateTime(new Date().getTime() + "");
				text.setMsgType(msgType);

				respMessage = MessageUtil.textMessageToXml(text);

			} /*
				 * else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT))
				 * {// 事件推送 String eventType = requestMap.get("Event");// 事件类型
				 * 
				 * if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {//
				 * 订阅 respContent = "欢迎关注xxx公众号！"; return
				 * MessageResponse.getTextMessage(fromUserName , toUserName ,
				 * respContent); } else if
				 * (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {//
				 * 自定义菜单点击事件 String eventKey = requestMap.get("EventKey");//
				 * 事件KEY值，与创建自定义菜单时指定的KEY值对应 logger.info("eventKey is:"
				 * +eventKey); return xxx; } } //开启微信声音识别测试 2015-3-30 else
				 * if(msgType.equals("voice")) { String recvMessage =
				 * requestMap.get("Recognition"); //respContent =
				 * "收到的语音解析结果："+recvMessage; if(recvMessage!=null){ respContent
				 * = TulingApiProcess.getTulingResult(recvMessage); }else{
				 * respContent = "您说的太模糊了，能不能重新说下呢？"; } return
				 * MessageResponse.getTextMessage(fromUserName , toUserName ,
				 * respContent); } //拍照功能 else
				 * if(msgType.equals("pic_sysphoto")) {
				 * 
				 * } else { return MessageResponse.getTextMessage(fromUserName ,
				 * toUserName , "返回为空"); }
				 */
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String eventType = requestMap.get("Event");// 事件类型
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {

					TextMessage text = new TextMessage();
					text.setContent("欢迎关注“博雅五洲证卡事业部机具报修服务号”！");
					text.setToUserName(fromUserName);
					text.setFromUserName(toUserName);
					text.setCreateTime(new Date().getTime() + "");
					text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

					respMessage = MessageUtil.textMessageToXml(text);
				}
				// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅

				}
				// 自定义菜单点击事件
				/*
				 * else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {
				 * 
				 * }
				 */else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					if (eventKey.equals("11")) {
						TextMessage text = new TextMessage();
						text.setContent("该功能暂未开发完，请自己动手维修。");
						text.setToUserName(fromUserName);
						text.setFromUserName(toUserName);
						text.setCreateTime(new Date().getTime() + "");
						text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

						respMessage = MessageUtil.textMessageToXml(text);
					} else if (eventKey.equals("12")) {
						TextMessage text = new TextMessage();
						text.setContent("您好，如需报障请拨打电话：400-8190-172");
						text.setToUserName(fromUserName);
						text.setFromUserName(toUserName);
						text.setCreateTime(new Date().getTime() + "");
						text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

						respMessage = MessageUtil.textMessageToXml(text);
					} else if (eventKey.equals("21")) {
						TextMessage text = new TextMessage();
						text.setContent("您好，BY-W3详情信息请咨询全球最帅技术总工程师王健策，联系方式：188 1301 6855");
						text.setToUserName(fromUserName);
						text.setFromUserName(toUserName);
						text.setCreateTime(new Date().getTime() + "");
						text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

						respMessage = MessageUtil.textMessageToXml(text);
					} else if (eventKey.equals("22")) {
						TextMessage text = new TextMessage();
						text.setContent("您好，Avansia详情信息请咨询全球最酷技术总工程师王健策，联系方式：188 1301 6855");
						text.setToUserName(fromUserName);
						text.setFromUserName(toUserName);
						text.setCreateTime(new Date().getTime() + "");
						text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

						respMessage = MessageUtil.textMessageToXml(text);
					} else if (eventKey.equals("23")) {
						TextMessage text = new TextMessage();
						text.setContent("您好，玛迪卡详情信息请咨询全球最吊技术总工程师王健策，联系方式：188 1301 6855");
						text.setToUserName(fromUserName);
						text.setFromUserName(toUserName);
						text.setCreateTime(new Date().getTime() + "");
						text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

						respMessage = MessageUtil.textMessageToXml(text);
					} else if (eventKey.equals("31")) {
						TextMessage text = new TextMessage();
						text.setContent("暂不接受投诉，功能未开发完毕。");
						text.setToUserName(fromUserName);
						text.setFromUserName(toUserName);
						text.setCreateTime(new Date().getTime() + "");
						text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

						respMessage = MessageUtil.textMessageToXml(text);
					} else if (eventKey.equals("32")) {
						TextMessage text = new TextMessage();
						text.setContent("暂不接受评价，功能未开发完毕。");
						text.setToUserName(fromUserName);
						text.setFromUserName(toUserName);
						text.setCreateTime(new Date().getTime() + "");
						text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

						respMessage = MessageUtil.textMessageToXml(text);
					}
				}
			}
		} catch (Exception e) {
			logger.error("error......");
		}
		return respMessage;
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 */
	@GetMapping(value = "/addPage")
	public String addPage() {
		return "admin/wechat/addProblem";
	}

	/**
	 * 添加页面
	 * 
	 * @return
	 */
	@GetMapping(value = "/problemPreview")
	public String problemPreview() {
		return "admin/wechat/problemPreview";
	}
	
	/**
	 * 我的账单
	 * 
	 * @return
	 */
	@GetMapping(value = "/problemsList")
	public String problemsList() {
		return "admin/wechat/problemsList";
	}


	/**
	 * 添加页面
	 * 
	 * @return
	 */
	@GetMapping(value = "/addPage2")
	@ResponseBody
	public String addPage2(HttpServletRequest request,String url) throws Exception{
		Ticket ticket = new Ticket();
		com.hewei.model.Ticket ticket2 = null;
		try {
			ticket2 = ticket.getTicket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = Sign.sign(ticket2.getTicket(), url+"&state=123");
		map.put("appId", "wx17717cc913f8ddc7");
		
		String code = url.substring(url.indexOf("=")+1);
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"
	                .replace("APPID", "wx17717cc913f8ddc7").replace("SECRET", "2c035e7bf0a7bd90db8524d34d449dbe").replace("CODE", code);
		 HttpClient httpClient = new HttpClient();
		 GetMethod getMethod = new GetMethod(URL);
		 httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		 httpClient.executeMethod(getMethod);
		 // 读取内容
		 byte[] responseBody = getMethod.getResponseBody();
		 String strResp = new String(responseBody, "UTF-8");
		 Map<String, Object> openid = (Map<String, Object>) JSON.parse(strResp);
		 map.put("openid", openid.get("openid"));
		return JSON.toJSONString(map);
	}
	
	@GetMapping(value = "/addPage3")
	@ResponseBody
	public String addPage3(HttpServletRequest request,String url) throws Exception{
		Ticket ticket = new Ticket();
		com.hewei.model.Ticket ticket2 = null;
		try {
			ticket2 = ticket.getTicket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = Sign.sign(ticket2.getTicket(), url+"&state=123");
		map.put("appId", "wx17717cc913f8ddc7");
		String code = url.substring(url.indexOf("=")+1);
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"
	                .replace("APPID", "wx17717cc913f8ddc7").replace("SECRET", "2c035e7bf0a7bd90db8524d34d449dbe").replace("CODE", code);
		 HttpClient httpClient = new HttpClient();
		 GetMethod getMethod = new GetMethod(URL);
		 httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		 httpClient.executeMethod(getMethod);
		 // 读取内容
		 byte[] responseBody = getMethod.getResponseBody();
		 String strResp = new String(responseBody, "UTF-8");
		 Map<String, Object> openid = (Map<String, Object>) JSON.parse(strResp);
		 map.put("openid", openid.get("openid"));
		 
		 //根据用户微信号获取基本信息
		 Map<String, Object> usermap = malfunctionService.selectProblemView(openid.get("openid").toString());
		 if (null==usermap) {
			
		 }else{
			 String status = usermap.get("status").toString();
			 if (status.equals("0")) {
				usermap.put("status", "未派单");
			 }else if (status.equals("1")) {
				 usermap.put("status", "已派单");
			}else if (status.equals("2")) {
				usermap.put("status", "在途中");
			}else if (status.equals("3")) {
				usermap.put("status", "维修中");
			}else if (status.equals("4")) {
				usermap.put("status", "已完成");
			}
			 map.putAll(usermap);
		 }
		 return JSON.toJSONString(map);
	}
	
	
	@GetMapping(value = "/addPage4")
	@ResponseBody
	public String addPage4(HttpServletRequest request,String url) throws Exception{
		Ticket ticket = new Ticket();
		com.hewei.model.Ticket ticket2 = null;
		try {
			ticket2 = ticket.getTicket();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = Sign.sign(ticket2.getTicket(), url+"&state=123");
		map.put("appId", "wx17717cc913f8ddc7");
		String code = url.substring(url.indexOf("=")+1);
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"
	                .replace("APPID", "wx17717cc913f8ddc7").replace("SECRET", "2c035e7bf0a7bd90db8524d34d449dbe").replace("CODE", code);
		 HttpClient httpClient = new HttpClient();
		 GetMethod getMethod = new GetMethod(URL);
		 httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		 httpClient.executeMethod(getMethod);
		 // 读取内容
		 byte[] responseBody = getMethod.getResponseBody();
		 String strResp = new String(responseBody, "UTF-8");
		 Map<String, Object> openid = (Map<String, Object>) JSON.parse(strResp);
		 map.put("openid", openid.get("openid"));
		 
		 //根据维修人员微信id获取故障单
		 List<Malfunction> malfunctions = malfunctionService.selectProblemsList(openid.get("openid").toString());
		 map.put("malfunctions", malfunctions);
		 return JSON.toJSONString(map);
	}

	
	/**
     * 更新状态
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/updateStatus")
    @ResponseBody
    public Object updateStatus(String id) {
        
        boolean b = malfunctionService.updateStatus(id);
        if (b) {
            return renderSuccess("修改成功");
        } else {
            return renderError("修改失败");
        }
    }

	/**
	 * 添加
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/add")
	@ResponseBody
	public Object add(Malfunction malfunction, String reserveTime1, String openid,HttpServletRequest request) throws Exception {
		if (null == malfunction.getMachineId() || "".equals(malfunction.getMachineId())) {
			return renderError("机具号不能为空！");
		} else {
			MachineInfo machineInfo = machineInfoService.selectById(malfunction.getMachineId());
			if (null == machineInfo) {
				return renderError("系统中没有该机具号对应的机具，检查机具号或联系管理员！");

			} else {
				
				int wechatcount = malfunctionService.countByWechatUserId(openid);
				if (wechatcount>0) {
					return renderError("您有未完成的账单，请勿重复报障！");
				}else{
					malfunction.setBy1(openid);
					malfunction.setCreateTime(new Date());
					if (null == reserveTime1 || "".equals(reserveTime1)) {
						return renderError("请选择预约时间！");
					} else {
						System.out.println(reserveTime1);
						String year = reserveTime1.substring(0, 10);
						String time = reserveTime1.substring(11, 16);
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						try {
							Date date = simpleDateFormat.parse(year + " " + time);
							malfunction.setReserveTime(date);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						malfunction.setStatus("0");
						com.hewei.wechat.Token token = new com.hewei.wechat.Token();
						Token token2 = token.geToken();
						String image1, image2, image3;
						if (null != malfunction.getImage1()) {
							image1 = new String(malfunction.getImage1());
						} else {
							image1 = null;
						}
						if (null != malfunction.getImage2()) {
							image2 = new String(malfunction.getImage2());
						} else {
							image2 = null;
						}
						if (null != malfunction.getImage3()) {
							image3 = new String(malfunction.getImage3());
						} else {
							image3 = null;
						}
						String serverPath = this.getClass().getClassLoader().getResource("").getPath();
						int index = serverPath.indexOf("WEB-INF");
						serverPath = serverPath.substring(0, index);
						int t = malfunctionService.insertReturnId(malfunction);
						if (null != image1 && !"".equals(image1)) {
							String path = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+token2.getToken()+"&media_id="+image1;
							URL url = new URL(path);
							HttpURLConnection connection = (HttpURLConnection) url.openConnection();
							DataInputStream in = new DataInputStream(connection.getInputStream());
							byte[] data = new byte[10240];
							
							File file = new File(serverPath+"static/wechatImg/"+malfunction.getId().toString()+"_01.jpg");
							FileOutputStream fileOutputStream = new FileOutputStream(file);
							int count = 0;
							while ((count = in.read(data))>0) {
								fileOutputStream.write(data, 0, count);
							}
							fileOutputStream.flush();
							fileOutputStream.close();
							in.close();
							connection.disconnect();
						}else {
							BufferedImage image = ImageIO.read(new File(serverPath+"static/wechatImg/yaya.jpg"));
							//要想保存这个对象的话你要把image声明为BufferedImage 类型
							ImageIO.write(image, "jpg", new File(serverPath+"static/wechatImg/"+malfunction.getId().toString()+"_01.jpg"));
						}if (null != image2 && !"".equals(image2)) {
							String path = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+token2.getToken()+"&media_id="+image2;
							URL url = new URL(path);
							HttpURLConnection connection = (HttpURLConnection) url.openConnection();
							DataInputStream in = new DataInputStream(connection.getInputStream());
							byte[] data = new byte[10240];
							File file = new File(serverPath+"static/wechatImg/"+malfunction.getId().toString()+"_02.jpg");
							FileOutputStream fileOutputStream = new FileOutputStream(file);
							int count = 0;
							while ((count = in.read(data))>0) {
								fileOutputStream.write(data, 0, count);
							}
							fileOutputStream.flush();
							fileOutputStream.close();
							in.close();
							connection.disconnect();
						}else {
							BufferedImage image = ImageIO.read(new File(serverPath+"static/wechatImg/yaya.jpg"));
							//要想保存这个对象的话你要把image声明为BufferedImage 类型
							ImageIO.write(image, "jpg", new File(serverPath+"static/wechatImg/"+malfunction.getId().toString()+"_02.jpg"));
						}
						if (null != image3 && !"".equals(image3)) {
							String path = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+token2.getToken()+"&media_id="+image3;
							URL url = new URL(path);
							HttpURLConnection connection = (HttpURLConnection) url.openConnection();
							DataInputStream in = new DataInputStream(connection.getInputStream());
							byte[] data = new byte[10240];
							File file = new File(serverPath+"static/wechatImg/"+malfunction.getId().toString()+"_03.jpg");
							FileOutputStream fileOutputStream = new FileOutputStream(file);
							int count = 0;
							while ((count = in.read(data))>0) {
								fileOutputStream.write(data, 0, count);
							}
							fileOutputStream.flush();
							fileOutputStream.close();
							in.close();
							connection.disconnect();
						}else {
							BufferedImage image = ImageIO.read(new File(serverPath+"static/wechatImg/yaya.jpg"));
							//要想保存这个对象的话你要把image声明为BufferedImage 类型
							ImageIO.write(image, "jpg", new File(serverPath+"static/wechatImg/"+malfunction.getId().toString()+"_03.jpg"));
						}
						
						if (t>0) {
							return renderSuccess("报障成功，请耐心等待工程师上门提供服务！");
						} else {
							return renderError("报障失败！");
						}

					}
				}
				

			}
		}
	}
}
