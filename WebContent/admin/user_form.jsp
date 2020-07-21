<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New User | Evergreen Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
	<h3>Enter user information</h3>
		<form action="create_user" method="post"
			onsubmit="return vaidateFormInput()">
			<table>
				<tr>
					<td>Email:</td>
					<td><input id="email" type="text" name="email" size="20"></td>
				</tr>
				<tr>
					<td>Full name:</td>
					<td><input id="fullName" type="text" name="fullName" size="20"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input id="password" type="text" name="password" size="20"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<button type="submit">Save</button>
					<input type="button" value="Cancel"
						onclick="javascript:history.go(-1);"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
	<script type="text/javascript">
		function vaidateFormInput() {
			var fieldEmail = document.getElementById("email");
			var fieldFullName = document.getElementById("fullName");
			var fieldPassword = document.getElementById("password");
			
			if (fieldEmail.value.length == 0) {
				alert("Email can not be empty");
				fieldFullName.focus();
				return false;
			}
			
			if (fieldFullName.value.length == 0) {
				alert("Full Name can not be empty");
				fieldEmail.focus();
				return false;
			}
			
			if (fieldPassword.value.length == 0) {
				alert("Password can not be empty");
				fieldPassword.focus();
				return false;
			}
		}
	</script>
</html>
