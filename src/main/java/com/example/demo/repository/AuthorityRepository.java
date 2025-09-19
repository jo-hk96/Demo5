package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Authority;


//멤버의 권한을 조회
public interface AuthorityRepository extends JpaRepository<Authority ,Long>{

}
