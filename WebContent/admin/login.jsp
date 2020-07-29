<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<meta charset="UTF-8">
<title>Admin login</title>
</head>
<body>
	<div align="center">
		<h1>Book Store Administration</h1>
		<h4>Admin login</h4>
		<form id=loginForm action="login" method="post">
			<table class="form">
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" name="email" id="email"></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="text" name="password"
						id="password"></td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit">Login</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			rules : {
				email : {
					required : true,
					email : true
				},

				password : "required",
			},

			messages : {
				email : {
					required : "Email can not be empty",
					email : "Please enter a valid email address"
				},
				password : "Password can not be blank"
			}
		});
	});
</script>
</html>