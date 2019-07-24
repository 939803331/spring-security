package com.ph.springsecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class LoginController {

	@Autowired
	private SessionRegistry sessionRegistry;


	@RequestMapping("/")
	public String showHome() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("当前登陆用户：" + name);

		return "home.html";
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login.html";
	}

	@GetMapping("/admin")
	@ResponseBody
	@PreAuthorize("hasPermission('/admin','r')")
	public String printAdminR() {
		return "如果你看见这句话，说明你访问/admin路径具有r权限";
	}

	@RequestMapping("/admin/c")
	@ResponseBody
	@PreAuthorize("hasPermission('/admin','c')")
	public String printAdminC() {
		return "如果你看见这句话，说明你访问/admin路径具有c权限";
	}

	@GetMapping("/user")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_USER')")
	public String printUser() {
		return "如果你看见这句话，说明你有ROLE_USER角色";
	}

	@RequestMapping("/login/error")
	public void loginError(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		AuthenticationException exception =
				(AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		try {
			response.getWriter().write(exception.toString());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/login/invalid")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public String invalid() {
		return "Session 已过期，请重新登录";
	}

	@GetMapping("/kick")
	@ResponseBody
	public String removeUserSessionByUsername(@RequestParam String username) {
		int count = 0;

		// 获取session中所有的用户信息
		List<Object> users = sessionRegistry.getAllPrincipals();
		for (Object principal : users) {
			if (principal instanceof User) {
				String principalName = ((User)principal).getUsername();
				if (principalName.equals(username)) {
					// 参数二：是否包含过期的Session
					List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
					if (null != sessionsInfo && sessionsInfo.size() > 0) {
						for (SessionInformation sessionInformation : sessionsInfo) {
							sessionInformation.expireNow();
							count++;
						}
					}
				}
			}
		}
		return "操作成功，清理session共" + count + "个";
	}

	@GetMapping("/me")
	@ResponseBody
	public Object me(Authentication authentication) {
		return authentication;
	}
}
