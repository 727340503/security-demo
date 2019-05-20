package com.imooc.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class ImoocAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * spring启动的时候会自动注册
	 */
	/*@Autowired
	private ObjectMapper objectMapper;*/
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		logger.info("imooc登陆失败");
		
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json;charset=UTF-8");
//		response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
		response.getWriter().write(exception.getMessage());
//		super.onAuthenticationFailure(request, response, exception);
		
		/*if (AuthenticationResponseTypeEnum.JSON.equals(securityProperties.getBrowser().getAuthentionResponseType())) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
		} else if (AuthenticationResponseTypeEnum.DIRECT.equals(securityProperties.getBrowser().getAuthentionResponseType())) {
			logger.error(exception.getMessage());
			super.onAuthenticationFailure(request, response, exception);
		}*/
		
	}

}
