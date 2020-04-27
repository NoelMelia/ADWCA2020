<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Page</title>
<link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>List of Products</h1>
	<table>
		<tr>
			<th>Product ID</th>
			<th>Description</th>
			<th>Quantity in Stock</th>
		</tr>
		<tr>
			<c:forEach items="${product}" var="product">
				<tr>
					<td>${product.pId}</td>
					<td>${product.pDesc}</td>
					<td>${product.qtyInStock}</td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	<table>
		<td><a href="/index.html">Home</a></td>
		<td><a href="/addProduct.html">Add Product</a></td>
		<td><a href="/showProducts.html">List Products</a></td>
		<td><a href="/showOrders.html">List Orders</a></td>
		<td><a href="/logout">Logout</a></td>
	</table>
</body>
</html>