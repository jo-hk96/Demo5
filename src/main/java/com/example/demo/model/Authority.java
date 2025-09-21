package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder

//Column 있어야 자동으로 만들어줌
//@AllArgsConstructor : 모든필드를 파라미터로 받는 생성자
@AllArgsConstructor
//@NoArgsConstructor : 파라미터가 없는 기본생성자 (lombok)
@NoArgsConstructor
public class Authority{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String authority;
	
	//member의 id를 들고오는 외래키
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
}
