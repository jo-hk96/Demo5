package com.example.demo.model;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;
@Data
public class MemberUserDetails implements UserDetails {
	// 이제 Member와 Authority를 사용해 스프링 시큐리티에서 사용할 
	// UserDetails 인터페이스를 구현한 MemberUserDetails 클래스를 작성
    // User Details 디폴트 구현 getUsername(), getPassword(), getAuthorities()
	private String username;
	private String password;
	private List<SimpleGrantedAuthority> authorities;
	private String displayName;
	private Long memberId;
	
	
	public MemberUserDetails(Member member ,  List<Authority> authorities) {
		super();
		this.username = member.getEmail();
		this.password = member.getPassword();
		this.displayName = member.getName();
		this.memberId = member.getId();
		this.authorities = authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
							.toList();
		
		
	}
}
