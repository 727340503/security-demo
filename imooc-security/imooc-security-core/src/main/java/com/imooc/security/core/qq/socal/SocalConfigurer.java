package com.imooc.security.core.qq.socal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableSocial
public class SocalConfigurer extends SocialConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired(required=false)
	private ConnectionSignUp connectionSignUp;

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		repository.setTablePrefix("imooc_");
		
		if(null != connectionSignUp) {
			repository.setConnectionSignUp(connectionSignUp);
		}
		
		return repository;
	}
	
	@Bean
	public SpringSocialConfigurer springSocialConfigurer() {
		return new ImoocSpringSocialConfigurer();
	}
	
}
