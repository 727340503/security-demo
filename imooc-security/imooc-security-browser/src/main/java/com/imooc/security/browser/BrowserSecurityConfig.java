package com.imooc.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.imooc.security.browser.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.security.core.validate.code.ValidateCodeFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
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
	
	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);//首次执行时打开
		return tokenRepository;
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
			.rememberMe()//记住我功能
				.tokenRepository(tokenRepository())
				.tokenValiditySeconds(3600)//session过期秒数
				.userDetailsService(userDetailsService)
				.and()
			.sessionManagement()//session 管理
				.invalidSessionUrl("/session/invalid")
				.maximumSessions(1)
//				.maxSessionsPreventsLogin(true)
//				.expiredSessionStrategy(expiredSessionStrategy)
				.and()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/logoutSuccess")
				.deleteCookies("JESSIONID")
				.and()
			.authorizeRequests()
				.antMatchers("/loginPage","/logout","/code/image","/session/invalid","/logoutSuccess").permitAll()
				.anyRequest()//任何请求
				.authenticated()
				.anyRequest()
				.access("@rbacPermission.hasPermission(request, authentication)");//都需要认证
	}
	
}
