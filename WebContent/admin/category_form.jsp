<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category List | Online Books Store</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h3>
			<c:if test="${category eq null}">
				Enter new category information
			</c:if>
			<c:if test="${category ne null}">
				Edit category information
			</c:if>
		</h3>
		<c:if test="${category eq null}">
			<form action="create_category" method="post" id="categoryForm">
		</c:if>
		<c:if test="${category ne null}">
			<form action="update_category" method="post" id="categoryForm">
				<input type="hidden" name="categoryId"
					value="${category.categoryId}">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Name:</td>
				<td align="left"><input id="name" type="text" name="name"
					size="20" value="${category.name}"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script>
	$(document).ready(function() {
		$("#categoryForm").validate({
			rules : {
				name : "required"
			},
			messages : {
				name : "Name can not blank."
			}
		})

	});
	$("#buttonCancel").click(function() {
		history.go(-1);
	});
</script>
</html>