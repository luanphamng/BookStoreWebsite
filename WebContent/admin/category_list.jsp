<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style.css">
<title>Category List | Online Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<h1 align="center" class="pageheading">Categories Management</h1>
	<c:if test="${message ne null}">
		<h3 align="center">${message}</h3>
	</c:if>
	<h3 align="center"><a href="category_form.jsp">Create new category</a></h3>
	<div align="center">
		<table border="1">
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="category" items="${listCategory}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${category.name}</td>
					<td><a href="edit_category?categoryId=${category.categoryId}">Edit</a>
						| <a href="javascript:confirmDelete(${category.categoryId})">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script>
	function confirmDelete(categoryId) {
		if (confirm('Are you sure you want to delete the category with ID '
				+ categoryId + '?')) {
			window.location = 'delete_category?id=' + categoryId;
		}
	}
</script>
</html>