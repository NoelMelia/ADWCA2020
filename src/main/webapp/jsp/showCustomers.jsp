<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Customer</title>
<link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>List of customers</h1>
	<table>
		<c:forEach items="${customers}" var="customers">
			<h2>${customers.cId} ${customers.cName}</h2>
			<h3>${customers.cName}'s Orders</h3>

			<table>
				<tr>
					<th>Order ID</th>
					<th>Quantity</th>
					<th>Product Id</th>
					<th>Description</th>
				</tr>
				<c:forEach items="${customers.orders}" var="order">
					<tr>
						<td>${order.oId }</td>
						<td>${order.qty}</td>
						<td>${order.prod.pId }</td>
						<td>${order.prod.pDesc}</td>
					</tr>
				</c:forEach>

			</table>
			
		</c:forEach>
	</table>
	<br> 
	<a href="/">Home</a>
	<a href="/addCustomer.html">Add Customer</a>
	<a href="/showProducts.html">List Products</a>
	<a href="/showOrders.html">List Orders</a>
	<a href="/logout">Logout</a></td>
</body>
</html>