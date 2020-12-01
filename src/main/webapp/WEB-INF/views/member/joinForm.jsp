<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>가입양식</h2>

	<form method="post" action="${pageContext.request.contextPath}/member/join" id="registerForm" >
		<!-- 스프링 security 4에선 POST 전송시무조건 csrt 를 보내야 한다. (GET은 안보내도 됨)-->
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
		<table style="width: 600px">
			<tr>
				<td width="100">ID</td>
				<td><input type="text" name="id" id='id'> 
				</td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" id="password" name="password">
					</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" id="name" name="name">
				
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
				<input type="text" id="email" name="email">
				
				</td>
			</tr>
			
			<tr>
				<td>User Type</td>
				<td>
					<input type=radio value="0" name="userType">Role_USER  
					<input type=radio value="1" name="userType">Role_ADMIN
				</td>
			</tr>
			
			

			<tr>
				<td colspan="2">
				<input type="submit" value="가입"> 
				<input type="reset" value="다시작성"></td>
			</tr>
		</table>
	</form>
</body>
</html>