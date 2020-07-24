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
	<h1>User Management</h3>
	
	<c:if test="${message != null}">
		<div align="center">
			<p><i>${message}</i></p>
		</div>
	</c:if>
	
	<h3><a href="user_form.jsp">Create new user</a></h3>
		<table border="1">
			<tr>
				<th>No.</th>
				<th>Id</th>
				<th>Email</th>
				<th>FullName</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="users" items="${listUsers}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${users.userId}</td>
					<td>${users.email}</td>
					<td>${users.fullName}</td>
					<td>
						<a href="edit_user?id=${users.userId}">Edit</a> |
						<a href="">Delete</a>
					</td>
				</tr>
			
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>
