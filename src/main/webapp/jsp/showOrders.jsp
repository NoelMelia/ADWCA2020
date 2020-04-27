<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Orders</title>
<link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>List of Orders</h1>
	<c:forEach items="${orders}" var="order">
		<tr>
			<h2>${order.oId}</h2>
		</tr>
		<table >
			<tr>
				<th>Quantity</th>
				<th>Order Date</th>
				<th>Customer ID</th>
				<th>Customer Name</th>
				<th>Product ID</th>
				<th>Description</th>
			</tr>
			<tr>
				<td>${order.qty}</td>
				<td>${order.orderDate}</td>
				<td>${order.cust.cId}</td>
				<td>${order.cust.cName}</td>
				<td>${order.prod.pId}</td>
				<td>${order.prod.pDesc}</td>
			</tr>
		</table>
	</c:forEach>
	<br>
	<table>
		<td><a href="/index.html">Home</a></td>
		<td><a href="/addOrder.html">Add Order</a></td>
		<td><a href="/showProducts.html">List Products</a></td>
		<td><a href="/showCustomers.html">List Customers</a></td>
		<td><a href="/logout">Logout</a></td>
	</table>
</body>
</html>