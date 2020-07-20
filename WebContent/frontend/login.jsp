<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Book - Online Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2>Please login:</h2>
		<form>
			Email: &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<input type="text"></br>
			Password: <input type="password"></br>
			<input type="submit" value="login">
		</form>
		<h2>New books:</h2>
		<h2>Best selling books:</h2>
		<h2>Most favored:</h2>
		<br/><br/>
	</div>
	
	<jsp:directive.include file="footer.jsp"/>
</body>
</html>
