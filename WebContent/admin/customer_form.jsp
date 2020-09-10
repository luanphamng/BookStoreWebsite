<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Form | Online Books Store</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h3>
			<c:if test="${customer eq null}">
				Enter new customer information
			</c:if>
			<c:if test="${customer ne null}">
				Edit customer information
			</c:if>
		</h3>
		<c:if test="${customer eq null}">
			<form action="create_customer" method="post" id="customerForm">
		</c:if>
		<c:if test="${customer ne null}">
			<form action="update_customer" method="post" id="customerForm">
				<input type="hidden" name="id"
					value="${customer.customerId}">
		</c:if>
		
		<table class="form">	
			<tr>
				<td align="right">Email:</td>
				<td align="left"><input id="email" type="text" name="email"
					size="20" value="${customer.email}"></td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input id="fullname" type="text" name="fullname"
					size="20" value="${customer.fullname}"></td>
			</tr>
			<tr>
				<td align="right">Address:</td>
				<td align="left"><input id="address" type="text" name="address"
					size="20" value="${customer.address}"></td>
			</tr>
			<tr>
				<td align="right">City:</td>
				<td align="left"><input id="city" type="text" name="city"
					size="20" value="${customer.city}"></td>
			</tr>
			<tr>
				<td align="right">Country:</td>
				<td align="left"><input id="country" type="text" name="country"
					size="20" value="${customer.country}"></td>
			</tr>
			<tr>
				<td align="right">Phone:</td>
				<td align="left"><input id="phone" type="text" name="phone"
					size="20" value="${customer.phone}"></td>
			</tr>
			<tr>
				<td align="right">Zip Code:</td>
				<td align="left"><input id="zipcode" type="text" name="zipcode"
					size="20" value="${customer.zipcode}"></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input id="password" type="text" name="password"
					size="20" value="${customer.password}"></td>
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
		$("#customerForm").validate({
			rules : {
				email : "required"
			},
			messages : {
				email : "Email can not be blank."
			}
		})

	});
	$("#buttonCancel").click(function() {
		history.go(-1);
	});
</script>
</html>