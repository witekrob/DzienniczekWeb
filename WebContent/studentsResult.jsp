<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import ="model.Person2" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>a oto rezulatat twoich działań</title>
</head>
<body bgcolor="lightgreen">
	<a href="/DzienniczekWeb/index.jsp">Powrót do strony głównej</a>
	<br>

<h1> a oto rezultat twoich działań</h1>

<%=request.getAttribute("option") %>
<br>
<% Person2 pers = new Person2(); %>
<%if (request.getAttribute("student")!=null){%>
<% pers = (Person2)request.getAttribute("student") ;%>
<table border="3">
<tr bgcolor="white" align="center" >
<th>Imię</th><th>Nazwisko</th><th>Pesel</th>
</tr>
<tr bgcolor="white" align="center">
<td> <%=pers.getName() %></td>
<td><%= pers.getSurname()%></td>
<td><%=pers.getPesel() %></td>

</tr>
<%} %>
</table>
</body>
</html>