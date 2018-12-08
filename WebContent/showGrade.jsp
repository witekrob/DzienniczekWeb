<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Person2,model.grade"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/DzienniczekWeb/index.jsp">Powrót do strony głównej</a>
	<br>
	<%
		List<Person2> students = (List<Person2>) request.getAttribute("lista");
		List<Integer> grades = new ArrayList<Integer>();
	%>
	<br>

	<br>
	<table>
		<table border="2">
			<tr>
				<th>Pesel</th>
				<th>Imię</th>
				<th>Nazwisko</th>
				<th>ocena</th>
			</tr>

			<tr align="center">
				<%
				
					Person2 pers = new Person2();
					for (Person2 per : students) {
						
				%>
				<td><%=per.getPesel()%></td>
				<td><%=per.getName()%></td>
				<td><%=per.getSurname()%></td>
				<%
				if (per.getOcenki() != null) {
					grades = per.getOcenki();
					Iterator iter = grades.iterator();
							while (iter.hasNext()) {
								int ocena = (Integer) iter.next();
				%>
				<td><%=ocena%></td>
				<%
						}
				%>
			
			
				<%
					}
		%> </tr> <%} %>
				
				
			
		</table>
</body>
</html>