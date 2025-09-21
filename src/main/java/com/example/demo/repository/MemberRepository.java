package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Member;


//회원의 정보를 가져온다
@Repository
public interface MemberRepository extends JpaRepository<Member ,Long> {
	Optional<Member> findByEmail(String email);
}
