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
<c:if test="${user eq null}">
	<title>New User | Evergreen Books Store</title>
</c:if>
<c:if test="${user ne null}">
	<title>Edit user | Evergreen Books Store</title>
</c:if>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h3>
			<c:if test="${user eq null}">
				Enter new user information
			</c:if>
			<c:if test="${user ne null}">
				Edit user information
			</c:if>
		</h3>
		<c:if test="${user eq null}">
			<form id="userForm" action="create_user" method="post">
		</c:if>
		<c:if test="${user ne null}">
			<form id="userForm" action="update_user" method="post">
				<input type="hidden" name="userId" value="${user.userId}">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Email:</td>
				<td align="left"><input id="email" type="text" name="email"
					value="${user.email}"></td>
			</tr>
			<tr>
				<td align="right">Full name:</td>
				<td align="left"><input id="fullName" type="text"
					name="fullName" value="${user.fullName}"></td>
			</tr>
			<tr>
				<c:if test="${user == null}">
					<td align="right">Password:</td>
					<td align="left"><input id="password" type="text"
						name="password"></td>
				</c:if>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
			</tr>
		</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#userForm").validate({
			rules : {
				email : {
					required : true,
					email : true
				},

				fullName : "required",
				password : "required",
			},

			messages : {
				email : {
					required : "Email can not be empty",
					email : "Please enter a valid email address"
				},
				fullName : "Please enter full name",
				password : "Password can not be blank"
			}
		});
		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	});
</script>
</html>
