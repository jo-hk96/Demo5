package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.model.Authority;
import com.example.demo.model.Member;
import com.example.demo.model.MemberUserDetails;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.MemberRepository;


//사용자가 이메일로 로그인을 시도하면, 
//데이터베이스에서 그 이메일과 관련된 정보를 찾아서, 
//Spring Security가 로그인 처리를 할 수 있도록 넘겨준다
@Configuration
public class SecurityConfig {
	
	@Autowired
	public AuthorityRepository authorityRepository;
	
	
	//db에서 회원 정보와 권한 정보를 가져오기
	//사용자 정보 설정
	//@Bean :spring 부품 : configuration 에서 주로 사용하는 어노테이션
	@Bean
	public UserDetailsService  userDetailsService(
							MemberRepository memberRepository,
							AuthorityRepository authorityRepository,
							PasswordEncoder passwordEncoder){
	return new  UserDetailsService(){
		
		// username 이 userid로 통일
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
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		// withDefaults()  오류 : The method withDefaults() is undefined for the type SecurityConfiguration
	    // withDefaults()는 아무 설정도 하지 않는 Customizer<T>를 돌려주는 도우미(“기본값으로 둔다”는 의미).
	    // import static org.springframework.security.config.Customizer.withDefaults; 필요
	    // 아니면 Customizer.withDefaults() 로 변경
		//페이지별 권한 설정
		http.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/","/home").permitAll()
					.requestMatchers("/member/**").hasAuthority("ROLE_ADMIN")
					.anyRequest().authenticated())//로그인 하지않은 사용자는 접근 할 수 없음
					.formLogin(Customizer.withDefaults())
					.logout(Customizer.withDefaults());
		return null;
	}
	
	//password BCrypt 암호화 해주는 함수
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
