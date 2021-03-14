package com.studyhelper.domain.member.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.studyhelper.domain.member.entity.enums.Role;
import com.studyhelper.domain.member.security.jwt.JwtAuthenticationFilter;
import com.studyhelper.domain.member.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final JwtTokenProvider jwtTokenProvider;
	private final MemberDetailService memberDetailService;

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		//모든 사용자 권한 허가
		security.authorizeRequests().antMatchers("/**").permitAll();
		security.authorizeRequests().antMatchers("/board/**").hasRole("HAVEGROUP");
		security.authorizeRequests().antMatchers("/chat/**").hasRole("HAVEGROUP");
		security.authorizeRequests().antMatchers("/team/**").hasRole("HAVEGROUP");
		security.authorizeRequests().antMatchers("/userpage/**").authenticated();
		//로그인 페이지 설정
		security.formLogin().loginPage("/login").defaultSuccessUrl("/userpage",true);
		//ajax통신시 corps
		security.csrf().disable();
		security.headers().frameOptions().sameOrigin();
		//권한이 없을때 보내주는 동작
		security.exceptionHandling().accessDeniedPage("/accessDenied");
		//로그아웃시 로그인페이지로 다시 보냄
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
		security.userDetailsService(memberDetailService);
		security.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), 
				UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
