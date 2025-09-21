package com.example.demo.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Member;


@RestController
public class ApiController {
	private List<Member> members = List.of(
			new Member(1L, "조홍규", "hongkyu@naver.co.kr" ,null),
			new Member(2L, "카리나", "Karina@naver.co.kr" ,null),
			new Member(3L, "윈터",   "Win@naver.co.kr" ,null),
			new Member(4L, "장원영", "Jang@naver.co.kr" ,null)
		);
	
	@GetMapping("/api/members")
	public List<Member> getMembers (Model model) {
		return members;
	}
	
}
