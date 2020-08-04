<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<title>Books List | Evergreen Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
	<h1 class="pageheading">Book Management</h1>
	
	<c:if test="${message != null}">
		<div align="center">
			<p><i>${message}</i></p>
		</div>
	</c:if>
	
	<h3><a href="new_book_form">Create new book</a></h3>
		<table border="1">
			<tr>
				<th>No.</th>
				<th>Id</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="book" items="${listBooks}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${book.bookId}</td>
					
					<td>
						<img src="data:image/jpg;base64,${book.base64Image}" width="84" height="110" />
					</td>
					
					<td>${book.title}</td>
					<td>${book.author}</td>
					<td>${book.category.name}</td>
					<td>$${book.price}</td>
					<td>
						<fmt:formatDate pattern='MM/dd/yyyy' value='${book.lastUpdateTime}' />
					</td>
					<td>
						<a href="edit_book?id=${book.bookId}">Edit</a> |
						<a href="javascript:void(0);" class="deleteLink" id="${book.bookId}">Delete</a>
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
			if(confirm("Are you sure you want to delete the book with ID" + bookId + '?')) {
				window.location = 'delete_book?id=' + bookId;
			}
		});
	});
});
</script>
</html>
