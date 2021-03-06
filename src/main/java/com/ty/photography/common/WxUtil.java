package com.ty.photography.common;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ty.photography.model.AccessToken;
import com.ty.photography.model.Dictionary;
import com.ty.photography.model.JsTicket;
import com.ty.photography.model.WxUserInfo;
import com.ty.photography.service.IDictionaryService;

/**
 * 微信端
 * 
 * 单例类
 * @author liyan
 *
 */
public class WxUtil implements MobileUtils{
	private Logger log = LoggerFactory.getLogger(WxUtil.class);

	private static WxUtil instance = new WxUtil();
	private String appId;
	private String secret;
	private AccessToken accessToken;
	private JsTicket jsTicket;
	private byte[] lock = new byte[0];
	private boolean refresh = false;
	private ApplicationContext ctx;
	private int count = 0;		//获取accessToken 计数器
	private String today;
	private boolean lockRefresh = false;	//如果每一天内获取accessToken 大于maxTimes，会锁定获取刷新accessToken的操作
	private int maxTimes=50000;	//最大刷新次数
	/**
	 * 下载图片url
	 */
	public static String mediaUtl="http://file.api.weixin.qq.com/cgi-bin/media/get";
	
	@Override
	public String getMediaUtl() {
		return mediaUtl;
	}
	
	private WxUtil() {
		ctx = new ClassPathXmlApplicationContext(
				"applicationContext-database.xml");
		appId = CommonUtils.parseProperties("WX_APPID");
		secret = CommonUtils.parseProperties("WX_SECRET");
		today = CommonUtils.dateToString(new Date());
		initAccessToken();
		initJsTicket();
	}
	//初始化accessToken
	private void initAccessToken() {
		accessToken = new AccessToken();
		IDictionaryService dictionaryService = (IDictionaryService) ctx
				.getBean("dictionaryServiceImpl");
		List<Dictionary> list = dictionaryService.findByType("wx");
		if (list.size() > 0) {
			for (Dictionary dictionary : list) {
				if (dictionary.getDataShowValue().equals("access_token")) {
					accessToken.setAccess_token(dictionary.getDateValue());
				}
				if (dictionary.getDataShowValue().equals("expires_in")) {
					if (dictionary.getDateValue() != null
							&& !dictionary.getDateValue().equals("")
							&& CommonUtils.isNumeric(dictionary.getDateValue())) {
						accessToken.setExpires_in(Integer.parseInt(dictionary
								.getDateValue()));
					}
				}
				if (dictionary.getDataShowValue().equals("create_time")) {
					if (dictionary.getDateValue() != null
							&& !dictionary.getDateValue().equals("")
							&& CommonUtils.isNumeric(dictionary.getDateValue())) {
						accessToken.setCreateTime(Long.parseLong(dictionary
								.getDateValue()));
					}
				}
				if (dictionary.getDataShowValue().equals("refresh_times")) {
					if (dictionary.getDateValue() != null
							&& !dictionary.getDateValue().equals("")
							&& CommonUtils.isNumeric(dictionary.getDateValue())) {
						count = Integer.parseInt(dictionary.getDateValue());
						today = CommonUtils.dateToString(dictionary
								.getCreateTime());
					}
				}
			}
		} else {
			//获取accessToken 并保存
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
					+ appId + "&secret=" + secret;
			String json = UrlUtil.httpsRequest(url, "GET", null);
			accessToken = CommonUtils.parseJson(json, AccessToken.class);
			accessToken.setCreateTime(System.currentTimeMillis());
			Dictionary dictionary = new Dictionary();
			dictionary.setId(CommonUtils.getUUID());
			dictionary.setDataType("wx");
			dictionary.setDateValue(accessToken.getAccess_token());
			dictionary.setDataShowValue("access_token");
			dictionary.setCreateTime(new Date());
			dictionaryService.save(dictionary);

			dictionary.setId(CommonUtils.getUUID());
			dictionary.setDataShowValue("expires_in");
			dictionary
					.setDateValue(String.valueOf(accessToken.getExpires_in()));
			dictionaryService.save(dictionary);

			dictionary.setId(CommonUtils.getUUID());
			dictionary.setDataShowValue("create_time");
			dictionary
					.setDateValue(String.valueOf(accessToken.getCreateTime()));
			dictionaryService.save(dictionary);

			dictionary.setId(CommonUtils.getUUID());
			dictionary.setDateValue("1");
			dictionary.setDataShowValue("refresh_times");
			dictionaryService.save(dictionary);
		}
	}
	//初始化jsTicket
	private void initJsTicket() {
		jsTicket = new JsTicket();
		IDictionaryService dictionaryService = (IDictionaryService) ctx
				.getBean("dictionaryServiceImpl");
		List<Dictionary> list = dictionaryService.findByType("wx_js");
		if (list.size() > 0) {
			for (Dictionary dictionary : list) {
				if (dictionary.getDataShowValue().equals("ticket")) {
					jsTicket.setTicket(dictionary.getDateValue());
				}
				if (dictionary.getDataShowValue().equals("expires_in")) {
					if (dictionary.getDateValue() != null
							&& !dictionary.getDateValue().equals("")
							&& CommonUtils.isNumeric(dictionary.getDateValue())) {
						jsTicket.setExpires_in(Integer.parseInt(dictionary
								.getDateValue()));
					}
				}
				if (dictionary.getDataShowValue().equals("create_time")) {
					if (dictionary.getDateValue() != null
							&& !dictionary.getDateValue().equals("")
							&& CommonUtils.isNumeric(dictionary.getDateValue())) {
						jsTicket.setCreateTime(Long.parseLong(dictionary
								.getDateValue()));
					}
				}
			}
		} else {
			//获取jsTicket
			String url = getTicketUrl();
			String result = UrlUtil.httpsRequest(url, "GET", null);
			if (result.contains("\"errcode\":40001")
					|| result.contains("\"errcode\":40014")
					|| result.contains("\"errcode\":42001")) {
				//accessToken过期，重新获取
				refreshAccessToken(true);
				url = getTicketUrl();
				result = UrlUtil.httpsRequest(url, "GET", null);
			}
			jsTicket = CommonUtils.parseJson(result, JsTicket.class);
			jsTicket.setCreateTime(System.currentTimeMillis());
			Dictionary dictionary = new Dictionary();
			dictionary.setId(CommonUtils.getUUID());
			dictionary.setDataType("wx_js");
			dictionary.setCreateTime(new Date());
			dictionary.setDateValue(jsTicket.getTicket());
			dictionary.setDataShowValue("ticket");
			dictionaryService.save(dictionary);

			dictionary.setId(CommonUtils.getUUID());
			dictionary.setDataShowValue("expires_in");
			dictionary.setDateValue(String.valueOf(jsTicket.getExpires_in()));
			dictionaryService.save(dictionary);

			dictionary.setId(CommonUtils.getUUID());
			dictionary.setDataShowValue("create_time");
			dictionary.setDateValue(String.valueOf(jsTicket.getCreateTime()));
			dictionaryService.save(dictionary);
		}
		JsTicketMonitor monitor = new JsTicketMonitor(dictionaryService);
		monitor.process();
		Thread t = new Thread(monitor);
		t.start();
	}
	
	private String getTicketUrl(){
		return "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
				+ accessToken.getAccess_token() + "&type=jsapi";
	}

	class JsTicketMonitor implements Runnable {
		
		private IDictionaryService dictionaryService; 
		public JsTicketMonitor(IDictionaryService dictionaryService){
			this.dictionaryService = dictionaryService;
		}
		
		//定期更新ticket
		private void process(){
				if (jsTicket.isExpires()) {
					String url = getTicketUrl();
					String result = UrlUtil.httpsRequest(url, "GET", null);
					if (result.contains("\"errcode\":40001")
							|| result.contains("\"errcode\":40014")
							|| result.contains("\"errcode\":42001")) {
						refreshAccessToken(true);
						url = getTicketUrl();
						result = UrlUtil.httpsRequest(url, "GET", null);
					}
					jsTicket = CommonUtils.parseJson(result, JsTicket.class);
					jsTicket.setCreateTime(System.currentTimeMillis());

					Dictionary dictionary = new Dictionary();
					dictionary.setDataType("wx_js");
					dictionary.setDataShowValue("ticket");
					dictionary.setDateValue(jsTicket.getTicket());
					dictionary.setCreateTime(new Date());
					dictionaryService.updateValue(dictionary);

					dictionary.setDataShowValue("expires_in");
					dictionary.setDateValue(String.valueOf(jsTicket
							.getExpires_in()));
					dictionaryService.updateValue(dictionary);

					dictionary.setDataShowValue("create_time");
					dictionary.setDateValue(String.valueOf(jsTicket
							.getCreateTime()));
					dictionaryService.updateValue(dictionary);
				}
		}
		@Override
		public void run() {
			while (true) {
				process();
				try {
					Thread.sleep(900000); // 15分钟
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	public static WxUtil getInstance() {
		return instance;
	}
	
	@Override
	public void action(Command command){
		boolean isNew = refreshAccessToken(false);
		if(isNew){
			command.action(isNew);
		}
	}

	/**
	 * 同步获取access_token，避免重复获取
	 * 
	 * @param privilege
	 * @return
	 */
	private boolean refreshAccessToken(boolean privilege) {
		synchronized (lock) {
			Date now = new Date();
			if (!today.equals(CommonUtils.dateToString(now)) || count < maxTimes) {
				lockRefresh = false;
			}
			if ((!refresh || privilege) && !lockRefresh) {
				String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
						+ appId + "&secret=" + secret;
				String json = UrlUtil.httpsRequest(url, "GET", null);
				accessToken = CommonUtils.parseJson(json, AccessToken.class);
				accessToken.setCreateTime(System.currentTimeMillis());
				IDictionaryService dictionaryService = (IDictionaryService) ctx
						.getBean("dictionaryServiceImpl");
				Dictionary dictionary = new Dictionary();
				dictionary.setDataType("wx");
				dictionary.setDataShowValue("access_token");
				dictionary.setDateValue(accessToken.getAccess_token());
				dictionary.setCreateTime(now);
				dictionaryService.updateValue(dictionary);
				dictionary.setDataShowValue("expires_in");
				dictionary.setDateValue(String.valueOf(accessToken
						.getExpires_in()));
				dictionaryService.updateValue(dictionary);
				dictionary.setDataShowValue("create_time");
				dictionary.setDateValue(String.valueOf(accessToken
						.getCreateTime()));
				dictionaryService.updateValue(dictionary);

				// 监控访问次数
				if (today.equals(CommonUtils.dateToString(now))) {
					count++;
				} else {
					count = 1;
				}
				if (count > maxTimes) {
					lockRefresh = true;
					log.info("-------微信 refreshAccessToken times:" + count);
				}
				dictionary.setDataShowValue("refresh_times");
				dictionary.setCreateTime(now);
				dictionary.setDateValue(String.valueOf(count));
				dictionaryService.updateValue(dictionary);
				log.info("-------refreshAccessToken times:" + count);
				refresh = true;
			}
			return true;
		}
	}

	/**
	 * 发送微信消息 
	 * 
	 * 发送失败，会重复发送三次。
	 * 
	 * @param openid
	 * @param content
	 * @return
	 */
	public boolean sendMessage(String openid, String content) {
		int i = 0;
		boolean isNew = false; // 是否刷新token
		StringBuilder message = new StringBuilder();
		message.append("{\"touser\":\"").append(openid).append("\",")
				.append("\"msgtype\":\"text\",").append("\"text\":{")
				.append("\"content\":\"").append(content).append("\"}}");

		if (accessToken == null
				|| (accessToken != null && accessToken.isExpires())) {
			isNew = refreshAccessToken(false);
		}
		boolean result = send(openid, message.toString(), isNew);
		if (!result) {
			while (i <= 2) {
				i++;
				isNew = refreshAccessToken(false);
				if (isNew) {
					result = send(openid, message.toString(), isNew);
					if (result) {
						break;
					}
				}
			}
		}
		return result;
	}
	/**
	 * 获取用户信息
	 * @param openid
	 * @return
	 */
	public WxUserInfo userInfo(String openid) {
		WxUserInfo userInfo = new WxUserInfo();
		int i = 0;
		boolean isNew = false; // 是否刷新token
		if (accessToken == null
				|| (accessToken != null && accessToken.isExpires())) {
			isNew = refreshAccessToken(false);
		}
		int isOK = getUserInfo(openid, isNew, userInfo);
		if (isOK == 1) {
			while (i <= 2) {
				i++;
				isNew = refreshAccessToken(false);
				if (isNew) {
					isOK = getUserInfo(openid, isNew, userInfo);
					if (isOK != 1) {
						break;
					}
				}
			}
		}
		if (isOK == 2) {
			userInfo = null;
		}
		return userInfo;
	}

	private int getUserInfo(String openid, boolean isNew, WxUserInfo userInfo) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ accessToken.getAccess_token()
				+ "&openid="
				+ openid
				+ "&lang=zh_CN";
		String result = UrlUtil.httpsRequest(url, "GET", null);
		if (result.contains("\"errcode\":40001")
				|| result.contains("\"errcode\":40014")
				|| result.contains("\"errcode\":42001")) {
			if (isNew) {
				refresh = false;
			}
			return 1;
		} else if (result.contains("errcode")) {
			log.error("---获取微信用户信息失败---openid:" + openid + ",errorInfo："
					+ result);
			return 2;
		} else {
			log.info("---获取微信用户信息---result:" + result);
			WxUserInfo user = CommonUtils.parseJson(result, WxUserInfo.class);
			userInfo.setCity(user.getCity());
			userInfo.setCountry(user.getCountry());
			userInfo.setGroupid(user.getGroupid());
			userInfo.setHeadimgurl(user.getHeadimgurl());
			userInfo.setLanguage(user.getLanguage());
			userInfo.setNickname(user.getNickname());
			userInfo.setOpenid(user.getOpenid());
			userInfo.setProvince(user.getProvince());
			userInfo.setRemark(user.getRemark());
			userInfo.setSex(user.getSex());
			userInfo.setSubscribe(user.getSubscribe());
			userInfo.setSubscribe_time(user.getSubscribe_time());
			userInfo.setUnionid(user.getUnionid());
			return 0;
		}
	}

	private boolean send(String openid, String content, boolean isNew) {
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
				+ accessToken.getAccess_token();
		String result = UrlUtil.httpsRequest(url, "POST", content);
		if (result.contains("\"errcode\":0")) {
			return true;
		} else if (result.contains("\"errcode\":40001")
				|| result.contains("\"errcode\":40014")
				|| result.contains("\"errcode\":42001")) {
			if (isNew) {
				refresh = false;
			}
			return false;
		} else {
			log.error("---发送微信消息失败---openid:" + openid + ",errorInfo：" + result);
			return true;
		}
	}

	public JsTicket getJsTicket() {
		return jsTicket;
	}

	public String getAppId() {
		return appId;
	}
	
	@Override
	public AccessToken getAccessToken() {
		return accessToken;
	}
	
	@Override
	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}
	public static void main(String[] args) {
		WxUtil instance = WxUtil.getInstance();
		instance.sendMessage("o0sIItz77XDDanft4Abik8_ue4E4", "恭喜你中了一等奖");
		WxUserInfo userInfo = instance.userInfo("o0sIItz77XDDanft4Abik8_ue4E4");
		System.out.println(userInfo);
	}
}
