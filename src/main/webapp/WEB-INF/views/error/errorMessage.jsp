<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<h4>${exception.message} / ${requestScope.errorMsg}</h4>

<a href="${pageContext.request.contextPath}/">메인</a>


</body>
</html>