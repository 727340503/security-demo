package com.imooc.security.core.qq.socal.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 因为封装了user的access token信息，该类只能是多实例的，并且不能通过spring依赖注入
 * @author RahulRui
 *
 */
public class QQApiImpl extends AbstractOAuth2ApiBinding implements QQApi{
	
	private String appId;
	
	private String openId;
	
	private static final String OPEN_ID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s";
	
	private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
	
	public QQApiImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;
		
		String openIdUrl = String.format(OPEN_ID_URL, accessToken);
		String openIdResult = getRestTemplate().getForObject(openIdUrl, String.class);
		
		System.out.println("Open id result : " + openIdResult);
		this.openId = StringUtils.substringBetween(openIdResult, "\"openid\":\"", "\"}");
	}

	@Override
	public QQUserInfo getUserInfo(){
		QQUserInfo result = null;
		try{
			String userInfoUrl = String.format(USER_INFO_URL, this.appId, this.openId);
			String userInfoResult = getRestTemplate().getForObject(userInfoUrl, String.class);
			System.out.println("获取qq用户信息结果 : " + userInfoResult);
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.readValue(userInfoResult, QQUserInfo.class);
			result.setOpenId(this.openId);
		}catch (Exception e) {
			throw new RuntimeException("获取QQ用户信息出错");
		}
		
		return result;
	}

}
