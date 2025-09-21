package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	private List<Member> members = List.of(
			new Member(1L, "조홍규", "SeojunYoon@hanbit.co.kr" ,null),
			new Member(2L, "윤광철", "KwangcheolYoon@hanbit.co.kr" ,null),
			new Member(3L, "공미영", "MiyeongKong@hanbit.co.kr" ,null),
			new Member(4L, "김도윤", "DoyunKim@hanbit.co.kr" ,null)
		);
	
	@GetMapping("/")
	public String getMembers(Model model) {
		model.addAttribute("members", members);
		return "/member-list";
	}

}
