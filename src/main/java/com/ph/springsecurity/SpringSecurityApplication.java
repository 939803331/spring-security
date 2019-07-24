package com.ph.springsecurity;

import com.ph.springsecurity.security.VerifyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	/**
	 * 注入验证码servlet
	 */
	//@Bean
	//public ServletRegistrationBean indexServletRegistration() {
	//	ServletRegistrationBean registration = new ServletRegistrationBean(new VerifyServlet());
	//	registration.addUrlMappings("/getVerifyCode");
	//	return registration;
	//}
}
