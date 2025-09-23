package com.example.demo.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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
	//Member Table 롤 로그인하기 : Custom 로그인
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
			var email = Username.toLowerCase(); //이메일 소문자
			Member member = memberRepository.findByEmailIgnoreCase(email).orElseThrow();
				
				//권한정보조회
			List<Authority> authorities = authorityRepository.findByMember(member);
			
				return new MemberUserDetails(member, authorities );
			}
		};
	}
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, RememberMeServices rememberMeServices)throws Exception{
		// withDefaults()  오류 : The method withDefaults() is undefined for the type SecurityConfiguration
	    // withDefaults()는 아무 설정도 하지 않는 Customizer<T>를 돌려주는 도우미(“기본값으로 둔다”는 의미).
	    // import static org.springframework.security.config.Customizer.withDefaults; 필요
	    // 아니면 Customizer.withDefaults() 로 변경
		//페이지별 권한 설정
		http.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/","/home").permitAll()//로그인하지않은 사용자도 볼수있음
					// member/** 시작하는 모든 URL은 ADMIN권한은 가진 계정만 접속가능
					.requestMatchers("/Member/**").hasAuthority("ROLE_ADMIN")
					.requestMatchers("/Message/**").hasAuthority("ROLE_ADMIN")
					.anyRequest().authenticated())//로그인 하지않은 사용자는 접근 할 수 없음
		
					.formLogin(login -> login
							.loginPage("/login") //controller
							.defaultSuccessUrl("/") //login 성공시 이동하는 주소
							.usernameParameter("username")
							.passwordParameter("password")
							.permitAll() //permit all for /login
						)
					.logout(logout -> logout
							.logoutUrl("/logout")
							.logoutSuccessUrl("/home")
							.permitAll() //permit all for /logout
							
						);
		
		
		//securityFilterChain 메서드는 http 객체에 설정한 모든 규칙을 build()해서 반환해야함
		return http.build();
	}
	
	
		//자동 로그인 구현
		@Bean
		PersistentTokenRepository PersistentTokenRepository(DataSource dataSource) {
			JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
			repository.setDataSource(dataSource);
			return repository;
		}
		
		
		
		//자동 로그인 구현
		 @Bean
		    RememberMeServices rememberMeServices(
		        UserDetailsService UserDetailsService,
		        PersistentTokenRepository tokenRepository) {
		        
		        return new PersistentTokenBasedRememberMeServices(
		               "myRememberMeKey",
		               UserDetailsService,
		               tokenRepository);
		    }
		
	//password BCrypt 암호화 해주는 함수
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
