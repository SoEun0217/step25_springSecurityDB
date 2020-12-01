package kosta.mvc.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kosta.mvc.model.vo.Authority;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AuthoritiesDAOImpl implements AuthoritiesDAO {
	
	private final SqlSession session;
	
	@Override
	public int insertAuthority(Authority authority) {
		return session.insert("authoritiesMapper.insertAuthority", authority);
	}

	@Override
	public List<Authority> selectAuthorityByUserName(String userName) {
		return session.selectList("authoritiesMapper.selectAuthorityByUserName", userName);
	}

}
