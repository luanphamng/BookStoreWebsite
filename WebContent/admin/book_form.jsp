<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="../css/style.css">
	<link rel="stylesheet" href="../css/jquery-ui.min.css">
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	<c:if test="${book eq null}">
		<title>New Book | Evergreen Books Store</title>
	</c:if>
	<c:if test="${book ne null}">
		<title>Edit Book | Evergreen Books Store</title>
	</c:if>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${book != null}">
				Edit Book
			</c:if>
			<c:if test="${book == null}">
				Create New Book
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<form action="create_book" method="post" id="bookForm" enctype="multipart/form-data">
			<table class="form">
				<tr>
					<td>Category:</td>
					<td>
						<select name="category">
							<c:forEach items="${listCategory}" var="category">
								<c:if test="${category.categoryId eq book.category.categoryId}">
									<option value="${category.categoryId}" selected>
								</c:if>
								<c:if test="${category.categoryId ne book.category.categoryId}">
									<option value="${category.categoryId}">
								</c:if>							
									${category.name}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">Title:</td>
					<td align="left"><input type="text" id="title" name="title" size="20" value="${book.title}" /></td>
				</tr>
				<tr>
					<td align="right">Author:</td>
					<td align="left"><input type="text" id="author" name="author" size="20" value="${book.author}" /></td>
				</tr>
				<tr>
					<td align="right">ISBN:</td>
					<td align="left"><input type="text" id="isbn" name="isbn" size="20" value="${book.isbn}" /></td>
				</tr>
				<tr>
					<td align="right">Publish Date:</td>
					<td align="left"><input type="text" id="publishDate" name="publishDate" size="20" 
						value="<fmt:formatDate pattern='MM/dd/yyyy' value='${book.publishDate}'/>" /></td>
				</tr>			
				<tr>
					<td align="right">Book Image:</td>
					<td align="left">
						<input type="file" id="bookImage" name="bookImage" size="20"/>
						<br/>
						<img id="thumbnail" alt="Image Preview" style="width:20%; margin-top: 10px"
							src="data:image/jpg;base64,${book.base64Image}"/>
					</td>
				</tr>
				<tr>
					<td align="right">Price:</td>
					<td align="left"><input type="text" id="price" name="price" size="20" value="${book.price}" /></td>
				</tr>
				<tr>
					<td align="right">Description:</td>
					<td align="left">
						<textarea rows="5" cols="50" name="description" id="description">${book.description}</textarea>
					</td>
				</tr>										
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
						<button id="buttonCancel">Cancel</button>
					</td>
				</tr>				
			</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#publishDate').datepicker();
		$('#bookImage').change(function() {
			showImageThumbnail(this);
		});

		$("#bookForm").validate({
			rules : {
				category : "required",
				title : "required",
				author : "required",
				isbn : "required",
				publishDate : "required",
				bookImage : "required",
				price : "required",
				description : "required"
			},

			messages : {
				category : "Please select a vategory for the book",
				title : "Please enter te title of the book",
				author : "Please enter author of the book",
				isbn : "Please enter isbn",
				publishDate : "Please enter publish date of the book",
				bookImage : "Please choose one image",
				price : "Please give price for the book",
				description : "Please give description for the book"
			}
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	});

	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];
		var reader = new FileReader();
		console.log(reader);
		reader.onload = function(e) {
			$('#thumbnail').attr('src', e.target.result);
		}

		reader.readAsDataURL(file);
	}
</script>
</html>
