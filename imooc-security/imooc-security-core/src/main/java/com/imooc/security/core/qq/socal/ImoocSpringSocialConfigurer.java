package com.imooc.security.core.qq.socal;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class ImoocSpringSocialConfigurer extends SpringSocialConfigurer{
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl("/qqLogin");
		return (T) filter;
	}

}
