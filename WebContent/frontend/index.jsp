<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Book - Online Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	&nbsp;&nbsp;&nbsp;&nbsp;
	<div align="center">
		<c:forEach var="category" items="${listCategory}" varStatus="status">
			<a href="view_category?id=${category.categoryId}"> 
				<font size="+1">
					<b>
						<c:out value="${category.name}"/>
					</b>
				</font>
			</a>
			<c:if test="${not status.last}">
				&nbsp; | &nbsp;
			</c:if>
		</c:forEach>

		<h2>New books:</h2>
		<h2>Best selling books:</h2>
		<h2>Most favored:</h2>
		<br /> <br />
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>
