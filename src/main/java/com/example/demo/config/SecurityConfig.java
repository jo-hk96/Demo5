package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.demo.model.Authority;
import com.example.demo.model.Member;
import com.example.demo.model.MemberUserDetails;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.MemberRepository;

@Configuration
public class SecurityConfig {
	
	@Autowired
	public AuthorityRepository authorityRepository;
	
	
	//db에서 회원 정보와 권한 정보를 가져오기
	//사용자 정보 설정
	//@Bean :spring 부품 : configuration 에서 주로 사용하는 어노테이션
	@Bean
	public UserDetailsService  userDetailsService(MemberRepository memberRepository,
							PasswordEncoder passwordEncoder){
		
	return new  UserDetailsService() {
		
		// username : 입력한 로그인 id -> 이메일
		@Override
		public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException{
				// db(Member)에서 사용자 id ( email )를 가져온다
				// var     email  = username.toLowerCase();
				// Member 정보 조회
			Member member = memberRepository.findByEmail(Username).orElseThrow();
				
				//권한정보조회
			List<Authority> authorities = authorityRepository.findByMember(member);
			
				return new MemberUserDetails(member, authorities );
			}
			
		};
		
}
	
	

	
	
	
	//password BCrypt 암호화 해주는 함수
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
