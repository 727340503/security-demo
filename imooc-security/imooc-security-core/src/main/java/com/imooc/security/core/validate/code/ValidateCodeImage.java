package com.imooc.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class ValidateCodeImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9137718056218043302L;

	private BufferedImage image;

	private ValidateCode validateCode;

	public ValidateCodeImage(BufferedImage image, ValidateCode validateCode) {
		this.image = image;
		this.validateCode = validateCode;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public ValidateCode getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(ValidateCode validateCode) {
		this.validateCode = validateCode;
	}

}
