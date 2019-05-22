package com.imooc.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ValidateCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9137718056218043302L;

	private String code;

	private LocalDateTime expiredTime;

	public ValidateCode(String code, Long expiredIn) {
		this.code = code;
		this.expiredTime = LocalDateTime.now().plusSeconds(expiredIn);
	}

	public ValidateCode(String code, LocalDateTime expiredTime) {
		this.code = code;
		this.expiredTime = expiredTime;
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(this.expiredTime);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(LocalDateTime expiredTime) {
		this.expiredTime = expiredTime;
	}

}
