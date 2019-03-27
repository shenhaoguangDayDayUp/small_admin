package com.smart.sso.server.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smart.mvc.config.ConfigUtils;

/**
 * 验证码缓存工具类
 * 
 * @author Frank_King
 */
public class CaptchaHelper {

	public static final String CACHE_CAPTCHA = "_captcha";

	public static void setInCache(final HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedImage image = new Captcha() {
			protected void setInCache(String captcha) {
				request.getSession().setAttribute(CACHE_CAPTCHA, captcha);
			}
		}.generate();

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		try {
			out.flush();
		}
		finally {
			out.close();
		}
	}

	public static boolean validate(HttpServletRequest request, String captcha) {
		//开发模式
		String isDevMode =ConfigUtils.getProperty("isDevMode");
		
		if("true".equals(isDevMode)&&"pass".equals(captcha)) {
			return true;
		}
		String sessionCaptcha = request.getSession().getAttribute(CaptchaHelper.CACHE_CAPTCHA).toString();
		return sessionCaptcha == null ? false : sessionCaptcha.equalsIgnoreCase(captcha);
	}
}