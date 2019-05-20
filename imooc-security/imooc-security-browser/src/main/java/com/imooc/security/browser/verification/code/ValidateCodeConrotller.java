package com.imooc.security.browser.verification.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.contsant.SecurityConstants;
import com.imooc.security.core.validate.code.ValidateCode;

@Controller
public class ValidateCodeConrotller {

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@GetMapping("/code/image")
	public void createCode(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

		// 生成图片
		ValidateCode imageCode = generateImageCode(request);
		
		System.out.println(imageCode.getImageCode());

		// 将随机数存到session
		sessionStrategy.setAttribute(new ServletWebRequest(request), SecurityConstants.DEFAULT_REQUEST_PARAMETER_IMAGECODE, imageCode);

		// 输出图片
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}

	private ValidateCode generateImageCode(HttpServletRequest request) {
		int width = 60;
		int height = 30;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Random random = new Random();

		Graphics g = image.getGraphics();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		g.setColor(getRandColor(200, 250));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x1, y1);
		}

		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 16);
		}


		g.dispose();

		return new ValidateCode(image, sRand, 60L);
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
