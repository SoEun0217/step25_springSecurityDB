<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>로그인</h2>
<!-- authentication-failure-url 이동시 사용 코드 
	if test="$ {param.fail=='fail' }">
	로그인 실패 했습니다. ID와 패스워드를 확인하세요.<p>
</ if -->

<c:if test="${not empty requestScope.errorMessage}">
	<span style="color:red">${requestScope.errorMessage}</span>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
<table style="width: 350px">
	<tr>
		<td width="70px">ID</td>
		<td><input type="text" name="id" size="30"></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input type=password name="password" size="31"></td>
	</tr>
	
	<tr>
		<td colspan="2"><input type="submit" value="로그인"></td>
	</tr>
</table>
</form>

</body>
</html>