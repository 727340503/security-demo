package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ValidateCode {

	private BufferedImage image;

	private String imageCode;

	private LocalDateTime expiredTime;

	public ValidateCode(BufferedImage image, String imageCode, Long expiredIn) {
		this.image = image;
		this.imageCode = imageCode;
		this.expiredTime = LocalDateTime.now().plusSeconds(expiredIn);
	}

	public ValidateCode(BufferedImage image, String imageCode, LocalDateTime expiredTime) {
		this.image = image;
		this.imageCode = imageCode;
		this.expiredTime = expiredTime;
	}
	
	public boolean isExpired() {
		return LocalDateTime.now().isAfter(this.expiredTime);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
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
