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
<title>Customers List | Evergreen Books Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
	<h1 class="pageheading">Customer Management</h1>
	
	<c:if test="${message != null}">
		<div align="center">
			<p><i>${message}</i></p>
		</div>
	</c:if>
	
	<h3><a href="create_customer">Create new customer</a></h3>
		<table border="1">
			<tr>
				<th>No.</th>
				<th>Id</th>
				<th>Email</th>
				<th>FullName</th>
				<th>Address</th>
				<th>City</th>
				<th>Country</th>
				<th>Phone</th>
				<th>Zip Code</th>
				<th>Password</th>
				<th>Action</th>
			</tr>
			<c:forEach var="customer" items="${listCustomers}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${customer.customerId}</td>
					
					
					<td>${customer.email}</td>
					<td>${customer.fullname}</td>
					<td>${customer.address}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td>${customer.phone}</td>
					<td>${customer.zipcode}</td>
					<td>${customer.password}</td>
					
					
					<td>
						<a href="edit_customer?id=${customer.customerId}">Edit</a> |
						<a href="javascript:confirmDelete(${customer.customerId})" class="deleteLink" id="${customer.customerId}">Delete</a>
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
			customerId = $(this).attr("id");
			if(confirm("Are you sure you want to delete the customer with ID" + customerId + '?')) {
				window.location = 'delete_customer?id=' + customerId;
			}
		});
	});
});
</script>
</html>
