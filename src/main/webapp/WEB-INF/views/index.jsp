<%@page
	import="org.springframework.security.core.context.SecurityContextImpl"%>
<%@page import="java.util.Enumeration"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 a{text-decoration: none;}
</style>

<script>
	function logout() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>

<body>
	<h2>My Spring Securiry DB인증 </h2>
	<!-- sec:authoize 태그 - 현 사용자가 특정 권한이 있으면 몸체의 내용을 처리한다.
	   access속성에 어떤 권한인지를 표현한다. - 스프링 시큐리티의 spEL-->
	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" /><p>
		<sec:authentication property="principal.name" /> 님 환영합니다. <!-- Authentication의 getPrincipal().getName() -> Principal은 Provider에서 Authentication 에 넣어준 VO(생성자 첫 매개변수) -->
	</sec:authorize>
	
	<p>

		<!-- 인증 : 로그인 한 경우 -->
	<ul>
		<sec:authorize access="permitAll">
			<!-- 권한 관계없이 나올 메뉴 -->
			<li><a href="${pageContext.request.contextPath}/">메인</a></li>
		</sec:authorize>
		
		<!-- 인증 안됐으면 -->
		<sec:authorize access="isAnonymous()">
			<!-- 또는 !isAuthenticated() 로 비교해도 된다.  로그인을 하지 않은 사용자-->
			<li><a href="${pageContext.request.contextPath}/member/loginForm">로그인 폼</a></li>
			<li><a href="${pageContext.request.contextPath}/member/joinForm">가입</a></li>
		</sec:authorize>
		
		<!-- 인증 됐으면 -->
		<sec:authorize access="isAuthenticated()">
			<!-- 관리자인 경우 -->
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="${pageContext.request.contextPath}/admin/main">Admin 메인</a></li>
			</sec:authorize>
			
			<!-- 일반회원이거나 관리자인 경우. 두개 이상의 role을 비교할 때 hasAnyRole() -->
			<sec:authorize access="hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')">
				<li><a href="${pageContext.request.contextPath}/member/main">Member
						메인</a></li>
			</sec:authorize>
			<li><a href="javascript:logout();">로그아웃</a></li>
		</sec:authorize>
	</ul>
	<p>

		
		<!-- 
로그아웃은 스프링시큐리티 4 부터는 로그아웃시 POST 방식으로 이동하며 /logout url로 요청한다. (따로 정의하지 않으면)
그리고 _csrf 를 요청파라미터로 보내야 한다.
 -->
	<form id="logoutForm" action="${pageContext.request.contextPath}/member/logout"
		method="post" style="display: none">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<hr>

</body>
</html>