package com.imooc.security.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.imooc.security.app.jwt.ImoocJwtTokenEnhancer;
import com.imooc.security.core.properties.OAuth2ClientProperties;
import com.imooc.security.core.properties.SecurityProperties;

@Configuration
@EnableAuthorizationServer
public class ImoocAuthenticationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired(required=false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	@Autowired(required=false)
	private ImoocJwtTokenEnhancer jwtTokenEnhancer;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
					.userDetailsService(userDetailsService)
					.tokenStore(tokenStore);
		
		if(null != jwtAccessTokenConverter && null != jwtTokenEnhancer) {
			TokenEnhancerChain chain = new TokenEnhancerChain();
			
			List<TokenEnhancer> enhancer = new ArrayList<TokenEnhancer>();
			enhancer.add(jwtTokenEnhancer);
			enhancer.add(jwtAccessTokenConverter);
			chain.setTokenEnhancers(enhancer);
			
			endpoints.tokenEnhancer(chain)
					.accessTokenConverter(jwtAccessTokenConverter);
		}
		
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder build = clients.inMemory();
		if(ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
			for(OAuth2ClientProperties client : securityProperties.getOauth2().getClients()) {
				build.withClient(client.getClientId())
						.secret(client.getClientSecret())
						.accessTokenValiditySeconds(client.getExpiredTime())
						.authorizedGrantTypes("refresh_token","password")
						.scopes("all", "read", "write");
			}
		}
	}
}
