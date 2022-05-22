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

<p><c:out value="${inventory.invId}" /></p>
<p><c:out value="${inventory.invType}" /></p>
<p><c:out value="${inventory.desciption}" /></p>
<p><c:out value="${inventory.insertedDate}" /></p>


</body>
</html>