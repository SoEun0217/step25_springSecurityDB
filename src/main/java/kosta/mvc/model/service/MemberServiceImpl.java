package kosta.mvc.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kosta.mvc.model.dao.AuthoritiesDAO;
import kosta.mvc.model.dao.MemberDAO;
import kosta.mvc.model.vo.Authority;
import kosta.mvc.model.vo.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

	private final MemberDAO memberDAO;
	private final AuthoritiesDAO authoritiesDAO;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public int joinMember(Member member) {
		//전달된 비밀번호 평문을 암호화 한다...
		String pass = passwordEncoder.encode(member.getPassword());//암호화
		member.setPassword(pass);
		
		//member테이블에 가입한다 - insert
		int result = memberDAO.insertMember(member);
		if(result==0)throw new RuntimeException("가입되지 않았습니다");
		
		//권한 테이블에 등록한다.
		if(member.getUserType()==null) throw new RuntimeException("userType오류로 가입 실패하였습니다.");
		
		//usertype에 따라 권한 테이블에 insert한다.
		result  = authoritiesDAO.insertAuthority(new Authority(member.getId(), RoleConstants.ROLE_MEMBER));
		if(result == 0 )throw new RuntimeException("권한 설정에 오류 발생했습니다.");
		if(member.getUserType().equals("1")) {
			result  = authoritiesDAO.insertAuthority(new Authority(member.getId(), RoleConstants.ROLE_ADMIN));
			if(result ==0) throw new RuntimeException("예외가 발생하여 다시 기입해주세요");
		}
		return result ; 
	}

}
