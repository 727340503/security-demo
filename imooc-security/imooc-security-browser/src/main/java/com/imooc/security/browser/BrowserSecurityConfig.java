package com.imooc.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.imooc.security.browser.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.security.core.validate.code.ValidateCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ValidateCodeFilter validateCodeFilter() {
		return new ValidateCodeFilter();
	}
	
	@Bean
	public ImoocAuthenticationSuccessHandler successHandler() {
		return new ImoocAuthenticationSuccessHandler();
	}
	
	@Bean
	public ImoocAuthenticationFailureHandler failureHandler() {
		return new ImoocAuthenticationFailureHandler();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.addFilterBefore(validateCodeFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http.formLogin()//用表单登录方式进行身份认证
//			.loginPage("/imooc-singin.html")
			.loginPage("/loginPage")
			.loginProcessingUrl("/authentication/form")
			.successHandler(successHandler())
			.failureHandler(failureHandler())
			.and()
			.authorizeRequests()
			.antMatchers("/loginPage","/code/image").permitAll()
			.anyRequest()//任何请求
			.authenticated();//都需要认证
	}
	
}
