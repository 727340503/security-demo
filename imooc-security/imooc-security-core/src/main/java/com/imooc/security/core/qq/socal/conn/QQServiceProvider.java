package com.imooc.security.core.qq.socal.conn;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.imooc.security.core.qq.socal.api.QQApi;
import com.imooc.security.core.qq.socal.api.QQApiImpl;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQApi>{
	
	private String appId;
	
	private final static String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";
	
	private final static String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
	
	public QQServiceProvider(String appId, String appSecret) {
//		super(new OAuth2Template(appId, appSecret, AUTHORIZE_URL, ACCESS_TOKEN_URL));
		super(new QQAuthTemplate(appId, appSecret, AUTHORIZE_URL, ACCESS_TOKEN_URL));
		this.appId = appId;
	}

	@Override
	public QQApi getApi(String accessToken) {
		return new QQApiImpl(accessToken, appId);
	}

}
