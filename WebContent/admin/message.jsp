<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List | Evergreen Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">	
	<c:if test="${message != null}">
		<div align="center">
			<p><i>${message}</i></p>
		</div>
	</c:if>
	
	<h3><a href="user_form.jsp">Create new user</a></h3>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>
