package com.ph.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;


@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//@Autowired
	//private CustomUserDetailsService userDetailsService;

	//@Autowired
	//private DataSource dataSource;

	@Autowired
	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

	//@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;

	//@Override
	//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(customAuthenticationProvider);
		//auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
		//	@Override
		//	public String encode(CharSequence charSequence) {
		//		return charSequence.toString();
		//	}
		//
		//	@Override
		//	public boolean matches(CharSequence charSequence, String s) {
		//		return s.equals(charSequence.toString());
		//	}
		//});
	//}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 如果有允许匿名的url，填在下面
				.antMatchers("/getVerifyCode","/login/invalid").permitAll()
				.anyRequest().authenticated()
				.and()
				// 设置登陆页
				.formLogin().loginPage("/login").permitAll()
				// 设置登陆成功页
				.successHandler(customAuthenticationSuccessHandler)
				.failureHandler(customAuthenticationFailureHandler);
				//.defaultSuccessUrl("/").permitAll()
				//.failureUrl("/login/error")
				// 自定义登陆用户名和密码参数，默认为username和password
				//                .usernameParameter("username")
				//                .passwordParameter("password")
				//.authenticationDetailsSource(authenticationDetailsSource)
				//.and()
				//.addFilterBefore(new VerifyFilter(), UsernamePasswordAuthenticationFilter.class)
				//.logout().permitAll()
				//.and().rememberMe()
				//.tokenRepository(persistentTokenRepository())
				//.tokenValiditySeconds(10)
				////.userDetailsService(userDetailsService)
				//.and()
				//.sessionManagement().invalidSessionUrl("/login/invalid")
				//.maximumSessions(1)
				// 当达到最大值时，是否保留已经登录的用户
				//.maxSessionsPreventsLogin(false)
				// 当达到最大值时，旧用户被踢出后的操作
				//.expiredSessionStrategy(new CustomExpiredSessionStrategy())
				//.sessionRegistry(new SessionRegistryImpl());

		//http.logout()
		//		.logoutUrl("/signout")
		//		.deleteCookies("JSESSIONID")
		//		.logoutSuccessHandler(customLogoutSuccessHandler);

		// 关闭CSRF跨域
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置拦截忽略文件夹，可以对静态资源放行
		web.ignoring().antMatchers("/css/**", "/js/**");
	}

	//记住我功能注入
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository(){
//		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//		//tokenRepository.setDataSource(dataSource);
//		// 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
////        tokenRepository.setCreateTableOnStartup(true);
//		return tokenRepository;
//	}

	/**
	 * 注入自定义PermissionEvaluator
	 */
	//@Bean
	//public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
	//	DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
	//	handler.setPermissionEvaluator(new CustomPermissionEvaluator());
	//	return handler;
	//}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
