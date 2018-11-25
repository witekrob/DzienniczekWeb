<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ page import= "model.PersonDatabase2, model.Person2"%>
    <%@page import = "java.util.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista ista wszystkich studentów</title>
</head>
<body>
<h1> A oto spis wszystkich studentów</h1>

<%List<Person2> fromDB = (List<Person2>)request.getAttribute("list");	
for (Person2 p : fromDB) {	
%>
<p>
<%=p.toString()
%>
<br>

<%
} %>
</>


</body>
</html>