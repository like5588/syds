package com.ty.photography.common;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ty.photography.model.AccessToken;
import com.ty.photography.model.Dictionary;
import com.ty.photography.model.WxUserInfo;
import com.ty.photography.service.IDictionaryService;

/**
 * 易信端
 * 
 * 单例类
 * @author liyan
 *
 */
public class YxUtil implements MobileUtils{
	private Logger log = LoggerFactory.getLogger(YxUtil.class);

	private static YxUtil instance = new YxUtil();
	private String appId;
	private String secret;
	private AccessToken accessToken;
	private byte[] lock = new byte[0];
	private boolean refresh = false;
	private ApplicationContext ctx;
	private int count = 0;		//获取accessToken 计数器
	private String today;
	private boolean lockRefresh = false;	//如果每一天内获取accessToken 大于maxTimes，会锁定获取刷新accessToken的操作
	private int maxTimes=150;	//最大刷新次数
	
	public static String mediaUtl="https://api.yixin.im/cgi-bin/media/get";
	/**
	 * 下载图片url
	 */
	@Override
	public String getMediaUtl() {
		return mediaUtl;
	}

	private YxUtil() {
		ctx = new ClassPathXmlApplicationContext(
				"applicationContext-database.xml");
		appId = CommonUtils.parseProperties("YX_APPID");
		secret = CommonUtils.parseProperties("YX_SECRET");
		today = CommonUtils.dateToString(new Date());
		initAccessToken();
	}
	//初始化accessToken
	private void initAccessToken() {
		accessToken = new AccessToken();
		IDictionaryService dictionaryService = (IDictionaryService) ctx
				.getBean("dictionaryServiceImpl");
		List<Dictionary> list = dictionaryService.findByType("yx");
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
			String url = "https://api.yixin.im/cgi-bin/token?grant_type=client_credential&appid="
					+ appId + "&secret=" + secret;
			String json = UrlUtil.httpsRequest(url, "GET", null);
			accessToken = CommonUtils.parseJson(json, AccessToken.class);
			accessToken.setCreateTime(System.currentTimeMillis());
			Dictionary dictionary = new Dictionary();
			dictionary.setId(CommonUtils.getUUID());
			dictionary.setDataType("yx");
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

	public static YxUtil getInstance() {
		return instance;
	}
	
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
				String url = "https://api.yixin.im/cgi-bin/token?grant_type=client_credential&appid="
						+ appId + "&secret=" + secret;
				String json = UrlUtil.httpsRequest(url, "GET", null);
				accessToken = CommonUtils.parseJson(json, AccessToken.class);
				accessToken.setCreateTime(System.currentTimeMillis());
				IDictionaryService dictionaryService = (IDictionaryService) ctx
						.getBean("dictionaryServiceImpl");
				Dictionary dictionary = new Dictionary();
				dictionary.setDataType("yx");
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
					log.info("------- 易信 refreshAccessToken times:" + count);
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
	 * 发送易信消息 
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
		String url = "https://api.yixin.im/cgi-bin/user/info?access_token="
				+ accessToken.getAccess_token()
				+ "&openid="
				+ openid;
		String result = UrlUtil.httpsRequest(url, "GET", null);
		if (result.contains("\"errcode\":40001")
				|| result.contains("\"errcode\":40014")
				|| result.contains("\"errcode\":40029")
				|| result.contains("\"errcode\":42001")) {
			if (isNew) {
				refresh = false;
			}
			return 1;
		} else if (result.contains("errcode")) {
			log.error("---获取易信用户信息失败---openid:" + openid + ",errorInfo："
					+ result);
			return 2;
		} else {
			log.info("---获取易信用户信息---result:" + result);
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
		String url = "https://api.yixin.im/cgi-bin/message/custom/send?access_token="
				+ accessToken.getAccess_token();
		String result = UrlUtil.httpsRequest(url, "POST", content);
		if (result.contains("\"errcode\":0")) {
			return true;
		} else if (result.contains("\"errcode\":40001")
				|| result.contains("\"errcode\":40014")
				|| result.contains("\"errcode\":40029")
				|| result.contains("\"errcode\":42001")) {
			if (isNew) {
				refresh = false;
			}
			return false;
		} else {
			log.error("---发送易信消息失败---openid:" + openid + ",errorInfo：" + result);
			return true;
		}
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
		YxUtil instance = YxUtil.getInstance();
		instance.sendMessage("o0sIItz77XDDanft4Abik8_ue4E4", "恭喜你中了一等奖");
		WxUserInfo userInfo = instance.userInfo("o0sIItz77XDDanft4Abik8_ue4E4");
		System.out.println(userInfo);
	}
}
