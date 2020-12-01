package kosta.mvc.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service //id="memberAuthenticationFailHandler"
public class MemberAuthenticationFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//security는 filter가 가로채서 처리중이기 떄문에 ModelAndView자체를 사용할 수 없다.
		request.setAttribute("errorMessage", exception.getMessage());
		request.getRequestDispatcher("/WEB-INF/views/member/loginForm.jsp").forward(request, response);
	}

}
