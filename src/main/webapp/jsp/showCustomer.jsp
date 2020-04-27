<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>List of Customers</h1>

<ol>
<li>${customer.cName}<li>
</ol>
<h3>${customer.cName}'s Order</h3>
<table>
  <tr>
   <th>Order ID</th>
   <th>Quantity</th>
   <th>Product Id</th>
   <th>Description</th>
  </tr>
  <tr>
    <c:forEach items="${customers}" 
                 var="customer">
      <tr> 
        <td>${customer.cId}</td>
        <td>${customer.cName}</td>
        <td>${customer.cAddr.town}</td>
        <td>${customer.cAddr.county}</td>
      </tr>
    </c:forEach>
  </tr>
</table>
</body>
</html>