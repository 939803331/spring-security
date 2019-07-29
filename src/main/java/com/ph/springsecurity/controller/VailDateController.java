package com.ph.springsecurity.controller;

import com.ph.springsecurity.entity.SmsCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Description:
 *
 * @Author: PiHao
 * Date: Created in 2019-07-29
 * Time: 20:38
 */
@RestController
public class VailDateController {
	private final static String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@GetMapping("/code/sms")
	public void createSmsCode(HttpServletRequest request, HttpServletResponse response, String mobile) throws IOException {
		SmsCode smsCode = createSMSCode();

		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY_SMS_CODE + mobile, smsCode);

	}

	private SmsCode createSMSCode() {
		String code = String.valueOf(new Random().nextInt(999999));
		System.out.println(code);
		return new SmsCode(code, 60);
	}


}
