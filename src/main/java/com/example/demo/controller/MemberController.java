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
	
	@GetMapping("/home")
	public String home() {
		return "redirect:/index.html";
	}
	
	@GetMapping("/Member/List")
	public String getMembers(Model model) {
		model.addAttribute("title", "<h2>회원 목록</h2>");
		model.addAttribute("utitle", "<h2>회원 목록</h2>");
		model.addAttribute("members", members);
		return "member-list";
	}
	
	@GetMapping("/Message/basic")
		public String getMessageBasic(Model model) {
		model.addAttribute("member" ,new Member(5L , "아이유","iu12@iu.com", "1234"));
		return "message/message-basic";
	}
	
}