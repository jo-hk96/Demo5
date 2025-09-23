package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Authority;
import com.example.demo.model.Member;


//멤버의 권한을 조회
public interface AuthorityRepository extends JpaRepository<Authority ,Long>{
	
	List<Authority> findByMember(Member member);

}
