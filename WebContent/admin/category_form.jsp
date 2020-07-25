<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category List | Online Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<c:if test="${category eq null}">
			<form action="create_category" method="post"
				onsubmit="return vaidateFormInput()">
		</c:if>
		<c:if test="${category ne null}">
			<form action="update_category" method="post"
				onsubmit="return vaidateFormInput()">
				<input type="hidden" name="categoryId"
					value="${category.categoryId}">
		</c:if>
		<table>
			<tr>
				<td>Name:</td>
				<td><input id="name" type="text" name="name" size="20"
					value="${category.name}"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button> <input type="button"
					value="Cancel" onclick="javascript:history.go(-1);">
				</td>
			</tr>
		</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script>
	function vaidateFormInput() {
		var name = document.getElementById("name");
		if (name.value.length == 0) {
			alert("Name can not be blanked!");
			name.focus();
			return false;
		}
	}
</script>
</html>