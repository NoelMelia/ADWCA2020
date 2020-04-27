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
		<h2>Can't Create Order</h2>
	</tr>
	<br>
	<table>
		<tr>
			<th>Product ID</th>
			<th>Customer ID</th>
			<th>Quantity in Stock</th>
		</tr>
		<tr>
			<td>${orders.prod.pId }</td>
			<td>${orders.cust.cId}</td>
			<td>${orders.qty}</td>
		</tr>


	</table>
	<br>
	<table>
	<tr>
		<td><a href="/index.html">Home</a></td>
		<td><a href="/addOrder.html">New Orders</a></td>
		<td><a href="/showOrders.html">List Orders</a></td>
		
	</tr>
	</table>
</body>
</html>