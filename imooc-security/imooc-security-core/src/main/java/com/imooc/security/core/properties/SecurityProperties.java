package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "imooc.security")
@Component(value = "globalSecurityProperties")
public class SecurityProperties {

	private SocialProperties social = new SocialProperties();

	public SocialProperties getSocial() {
		return social;
	}

	public void setSocial(SocialProperties social) {
		this.social = social;
	}

}
