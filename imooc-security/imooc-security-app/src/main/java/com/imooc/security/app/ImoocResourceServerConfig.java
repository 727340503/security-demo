package com.imooc.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import com.imooc.security.app.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.app.authentication.ImoocAuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class ImoocResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Autowired
	private ImoocAuthenticationSuccessHandler successHandler;
	
	@Autowired
	private ImoocAuthenticationFailureHandler failureHandler;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authentication/form")
			.successHandler(successHandler)
			.failureHandler(failureHandler)
				.and()
			.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
			.csrf().disable();;
	}
	
}
