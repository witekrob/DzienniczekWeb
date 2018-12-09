<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import= "model.PersonDatabase2, model.Person2"%>
    <%@page import = "java.util.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista ista wszystkich studentów</title>
</head>
<body bgcolor="lightgreen">

<a href="/DzienniczekWeb/index.jsp">Powrót do strony głównej</a>
<br>
<h1> A oto spis wszystkich studentów</h1>

<table border="3">
<tr bgcolor="white"> 
<th> Imię </th><th> Nazwisko</th><th>Pesel</th>
</tr>
<%List<Person2> fromDB = (List<Person2>)request.getAttribute("list");	
for (Person2 p : fromDB) {	
%>
<tr bgcolor="white">
<td> <%=p.getName() %></td> <td><%=p.getSurname() %> </td> <td><%=p.getPesel() %></td> 

</tr>
<%
} %>

</table>




</body>
</html>