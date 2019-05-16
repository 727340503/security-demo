package com.imooc.security.browser.verification.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

	private BufferedImage image;

	private String imageCode;

	private LocalDateTime expiredTime;

	public ImageCode(BufferedImage image, String imageCode, Long expiredIn) {
		this.image = image;
		this.imageCode = imageCode;
		this.expiredTime = LocalDateTime.now().plusSeconds(expiredIn);
	}

	public ImageCode(BufferedImage image, String imageCode, LocalDateTime expiredTime) {
		this.image = image;
		this.imageCode = imageCode;
		this.expiredTime = expiredTime;
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
