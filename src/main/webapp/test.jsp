<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<c:if test="${stockRecord != null}">
		<h1>A</h1>
	</c:if>
	
	<c:if test="${stockRecord == null}">
		<h1>B</h1>
	</c:if>
	
	<h1><c:out value='${stock.supId}' /></h1>
	<h1><c:out value='${stock.supItem}' /></h1>
	<h1><c:out value='${stock.quantity}' /></h1>
	<h1><c:out value='${stock.price}' /></h1>
	
	<p>Date/Time: <span id="date"></span></p>
	<script>
		var n =  new Date();
		var y = n.getFullYear();
		var m = n.getMonth() + 1;
		var	d = n.getDate();
		document.getElementById("date").innerHTML =  y + "-" + m + "-" + d;
	</script>  

</body>
</html>