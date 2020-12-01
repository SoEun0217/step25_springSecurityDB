package kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kosta.mvc.model.service.MemberService;
import kosta.mvc.model.vo.Member;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	/**
	 * ����ȭ�� 
	 * */
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * �α��� ��
	 * */
	@RequestMapping("/member/loginForm")
	public void loginForm() {}
	
	/**
	 * �����ϱ� ��
	 * */
	@RequestMapping("/member/joinForm")
	public void joinForm() {}
	
	/**
	 * ȸ������
	 * */
	@RequestMapping("/member/join")
	public String join(Member member) {
		memberService.joinMember(member);
		return "member/joinSuccess";
	}
	
	@RequestMapping("/admin/main")
	public void adminMain() {}
	
	@RequestMapping("/member/main")
	public void memberMain() {}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView error(Exception e) {
		return new ModelAndView("error/errorMessage", "errorMsg",e.getMessage());
	}
	
}
