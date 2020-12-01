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
		//���޵� ��й�ȣ ���� ��ȣȭ �Ѵ�...
		String pass = passwordEncoder.encode(member.getPassword());//��ȣȭ
		member.setPassword(pass);
		
		//member���̺� �����Ѵ� - insert
		int result = memberDAO.insertMember(member);
		if(result==0)throw new RuntimeException("���Ե��� �ʾҽ��ϴ�");
		
		//���� ���̺� ����Ѵ�.
		if(member.getUserType()==null) throw new RuntimeException("userType������ ���� �����Ͽ����ϴ�.");
		
		//usertype�� ���� ���� ���̺� insert�Ѵ�.
		result  = authoritiesDAO.insertAuthority(new Authority(member.getId(), RoleConstants.ROLE_MEMBER));
		if(result == 0 )throw new RuntimeException("���� ������ ���� �߻��߽��ϴ�.");
		if(member.getUserType().equals("1")) {
			result  = authoritiesDAO.insertAuthority(new Authority(member.getId(), RoleConstants.ROLE_ADMIN));
			if(result ==0) throw new RuntimeException("���ܰ� �߻��Ͽ� �ٽ� �������ּ���");
		}
		return result ; 
	}

}
