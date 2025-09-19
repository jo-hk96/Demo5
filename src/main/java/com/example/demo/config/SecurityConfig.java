package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.demo.repository.MemberRepository;

@Configuration
public class SecurityConfig {
	//@Bean :spring 부품 : configuration 에서 주로 사용하는 어노테이션
	
	
	
	//db에서 회원 정보와 권한 정보를 가져오기
	//사용자 정보 설정
	@Bean
	public UserDetailsService  userDetailsService(MemberRepository memberRepository,PasswordEncoder passwordEncoder){
		
	return new InMemoryUserDetailsManager(user,admin);
	}
	
	
	
	public UserDetails  userDetailsService(String member){

		throws UsernameNotFoundException{
		var    email = username.toLowerCase();s
		Member member = memberRepository.findByEmail(username).orElseThrow()
				
		// 권한정보 조회
		List<Authority> authorites = authorityRepository
						,findByMember(member);
		
				return new MemberUserDetails(member,authorites);
		
	}
		
	
	
	//password BCrypt 암호화 해주는 함수
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
