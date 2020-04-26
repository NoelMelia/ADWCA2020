<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>Error Creating the following Order</h1>
	<br>
	<tr>
		
			<h2>Error: Customer: ${orders.cust.cId} and/or Product: ${orders.prod.pId} doesnt exist</h2>
	</tr>
	<br>
	<table>
		<tr>
			<th>Product ID</th>
			<th>Customer ID</th>
			<th>Quantity</th>
		</tr>
		<tr>
			<td>${orders.prod.pId }</td>
			<td>${orders.cust.cId}</td>
			<td>${orders.qty}</td>
		</tr>
		
		
	</table>
	<br>
	<a href="/">Home</a>
	<a href="/addOrder.html">New Orders</a>
	<a href="/showOrders.html">List Orders</a>
</body>
</html>