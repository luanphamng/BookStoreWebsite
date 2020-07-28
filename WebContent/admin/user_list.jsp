<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<title>User List | Evergreen Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
	<h1 class="pageheading">User Management</h1>
	
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
			<c:forEach var="user" items="${listUsers}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>
						<a href="edit_user?id=${user.userId}">Edit</a> |
						<a href="javascript:void(0);" class="deleteLink" id="${user.userId}">Delete</a>
					</td>
				</tr>
			
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script>
$(document).ready(function(){
	$(".deleteLink").each(function(){
		$(this).on("click", function(){
			userId = $(this).attr("id");
			if(confirm("Are you sure you want to delete the user with ID" + userId + '?')) {
				window.location = 'delete_user?id=' + userId;
			}
		});
	});
});
</script>
</html>
