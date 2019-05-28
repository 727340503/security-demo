package com.imooc.security.core.qq.socal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.imooc.security.core.properties.QQProperties;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.properties.SocialProperties;
import com.imooc.security.core.qq.socal.api.QQApi;
import com.imooc.security.core.qq.socal.conn.QQConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "imooc.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<QQApi> createConnectionFactory() {
		
		final SocialProperties socalProperties = securityProperties.getSocial();
		final QQProperties qqProperties = socalProperties.getQq();
		
		return new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
	}
	
}
