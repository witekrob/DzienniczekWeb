<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>a oto rezulatat twoich działań</title>
</head>
<body>
<h1> a oto rezulatat twoich działań</h1>

<%=request.getAttribute("option") %>
<%if (request.getAttribute("student")!=null){ %>
<%=request.getAttribute("student") %>
<%} %>
</body>
</html>