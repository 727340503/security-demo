package com.imooc.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ValidateCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9137718056218043302L;

	private String imageCode;

	private LocalDateTime expiredTime;

	public ValidateCode(String imageCode, Long expiredIn) {
		this.imageCode = imageCode;
		this.expiredTime = LocalDateTime.now().plusSeconds(expiredIn);
	}

	public ValidateCode(String imageCode, LocalDateTime expiredTime) {
		this.imageCode = imageCode;
		this.expiredTime = expiredTime;
	}
	
	public boolean isExpired() {
		return LocalDateTime.now().isAfter(this.expiredTime);
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public LocalDateTime getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(LocalDateTime expiredTime) {
		this.expiredTime = expiredTime;
	}

}
