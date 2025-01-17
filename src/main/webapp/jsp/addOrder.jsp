<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<h1>New Order</h1>
	<form:form modelAttribute="orders">
		<table>
			<tr>
				<td>Customer:</td>
				<td><form:select path="cust.cName">
						
						<form:options items="${custNames}" />
					</form:select></td>
				<td><form:errors path="cust.cName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Product:</td>
				<td><form:select path="prod.pDesc">
						
						<form:options items="${prodNames}" />
					</form:select></td>
				<td><form:errors path="prod.pDesc" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Quantity:</td>
				<td><form:input path="qty"></form:input></td>
				<td><form:errors path="qty"></form:errors></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Order" /></td>
			</tr>

		</table>
	</form:form>

	<br>
	<table>
		
		<td><a href="/index.html">Home</a></td>
		<td><a href="/showCustomers.html">List Customers</a></td>
		<td><a href="/showProducts.html">List Products</a></td>
	
	</table>
	
	</body>
</html>