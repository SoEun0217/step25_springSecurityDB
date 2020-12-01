package kosta.mvc.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kosta.mvc.model.dao.AuthoritiesDAO;
import kosta.mvc.model.dao.MemberDAO;
import kosta.mvc.model.vo.Authority;
import kosta.mvc.model.vo.Member;
import lombok.RequiredArgsConstructor;

@Service // id = memberAuthenticationProvider
@RequiredArgsConstructor
public class MemberAuthenticationProvider implements AuthenticationProvider {

	private final MemberDAO memberDAO;
	private final AuthoritiesDAO authoritiesDAO;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//1. ���޹��� Authentication �ȿ��� id�� �����´�.
		String userId = authentication.getName();//userId;
		
		//2. id�� �ش��ϴ� ȸ������ �˻�..
		Member member = memberDAO.selectMemberById(userId);
		if(member==null) throw new UsernameNotFoundException(userId+ "�� ��ġ���� �ʽ��ϴ�.");
		
		//3. �˻��� ȸ�������� ����� ���޹��� Authentication�ȿ� �ִ� ��� ��
		String pass = authentication.getCredentials().toString();
		if(!passwordEncoder.matches(pass, member.getPassword())) {//matches�μ������߿�.��ġ�����ʴ´ٸ�
			throw new UsernameNotFoundException("��й�ȣ �����Դϴ�.");
		}
		
		//4. ��ΰ� ��ġ�ϸ� ...ȸ���� ���� ������ �˻��Ѵ�.
		List<Authority>list = authoritiesDAO.selectAuthorityByUserName(userId);
		if(list==null || list.size()==0)  throw new UsernameNotFoundException("������������ �����߻��Ͽ� ���� ����..");
		
		//5. Authentication�� ����Ŭ���� UsernamePasswordAuthenticationToken ��ü�� ���� �����Ѵ�.
		List<SimpleGrantedAuthority>authList = new ArrayList<SimpleGrantedAuthority>();
		for(Authority au:list) {
			authList.add(new SimpleGrantedAuthority(au.getRole()));
		}
		
		return new UsernamePasswordAuthenticationToken(member, null, authList);//principal�� member�� ���ִ�
		
	}

	
	/**
	 * �μ��� ���޵�authentication ������ �� �ִ� ��ȿ�� ��ü������ 
	 * �Ǵ��ϴ� �޼ҵ� - ���ϰ��� true�� ��� authenticate ȣ���
	 * */
	@Override
	public boolean supports(Class<?> authentication) {
		System.out.println("supports call......");
		
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);//Ʈ�翩�� ���� �޼ҵ尡 ȣ��ȴ�
	
	}
	

}
