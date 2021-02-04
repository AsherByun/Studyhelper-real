package com.studyhelper.member.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final MemberDetailService memberDetailService;
	
	public SecurityConfig(MemberDetailService memberDetailService) {
		this.memberDetailService = memberDetailService;
	}
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		//모든 사용자 권한 허가
		security.authorizeRequests().antMatchers("/").permitAll();
		security.authorizeRequests().antMatchers("/userpage/**").authenticated();
		security.authorizeRequests().antMatchers("/signup").permitAll();
		//로그인 페이지 설정
		security.formLogin().loginPage("/login").defaultSuccessUrl("/userpage",true);
		//ajax통신시 corps
		security.csrf().disable();
		//권한이 없을때 보내주는 동작
		security.exceptionHandling().accessDeniedPage("/accessDenied");
		//로그아웃시 로그인페이지로 다시 보냄
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
		
		security.userDetailsService(memberDetailService);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	} 
}
