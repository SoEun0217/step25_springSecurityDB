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
	 * 메인화면 
	 * */
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * 로그인 폼
	 * */
	@RequestMapping("/member/loginForm")
	public void loginForm() {}
	
	/**
	 * 가입하기 폼
	 * */
	@RequestMapping("/member/joinForm")
	public void joinForm() {}
	
	/**
	 * 회원가입
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
