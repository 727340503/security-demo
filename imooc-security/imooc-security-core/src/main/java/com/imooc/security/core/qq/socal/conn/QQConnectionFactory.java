package com.imooc.security.core.qq.socal.conn;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.imooc.security.core.qq.socal.api.QQApi;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQApi>{

	public QQConnectionFactory(String providerId, final String appId, final String appSecret) {
		super(providerId,  new QQServiceProvider(appId, appSecret), new QQApiAdapter());
	}

}
