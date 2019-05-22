package com.imooc.security.core.validate.mobile;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imooc.security.core.properties.contsant.SecurityConstants;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeException;

public class SmsCodeValidateFilter extends OncePerRequestFilter{
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if(StringUtils.equals("/authentication/mobile", request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
			try {
				validateCode(new ServletWebRequest(request));
			}catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}

	private void validateCode(ServletWebRequest servletWebRequest) {
		final ValidateCode validateCode = (ValidateCode) sessionStrategy.getAttribute(servletWebRequest, SecurityConstants.DEFAULT_REQUEST_PARAMETER_IMAGECODE);
		if(null == validateCode || StringUtils.isBlank(validateCode.getCode())) {
			throw new ValidateCodeException("验证码不存在");
		}
		
		final String code = servletWebRequest.getParameter(SecurityConstants.DEFAULT_REQUEST_PARAMETER_SMSCODE);
		if(StringUtils.isBlank(code)) {
			throw new ValidateCodeException("验证码不能为空");
		}
		
		if(validateCode.isExpired()) {
			throw new ValidateCodeException("验证码已过期");
		}
		
		if(!StringUtils.equals(validateCode.getCode(), code)) {
			throw new ValidateCodeException("验证码不正确");
		}
	}

}
