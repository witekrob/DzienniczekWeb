<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Person2,model.grade"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Baza ocen</title>
</head>
<body bgcolor="lightgreen">
<H2> witaj w wykazie ocen </H2>
<h2> wynik twojego zapytania :  <%=request.getAttribute("option") %></h2>

<br>
	<a href="/DzienniczekWeb/index.jsp">Powrót do strony głównej</a>
	<br>
	<%
		List<Person2> students = (List<Person2>) request.getAttribute("lista");
		List<Integer> grades = new ArrayList<Integer>();
	%>
	<br>
	<table border="4">
	
			<tr>
				<th>Pesel</th>
				<th>Imię</th>
				<th>Nazwisko</th>
				<th>ocena</th>
			</tr>

			<tr align="center" bgcolor="white">
				<%
					Person2 pers = new Person2();
					for (Person2 per : students) {
				%>
				<td bgcolor="white" align="center"><%=per.getPesel()%></td>
				<td bgcolor="white" align="center"><%=per.getName()%></td>
				<td bgcolor="white" align="center"><%=per.getSurname()%></td>
				<%
					if (per.getOcenki() != null) {
							grades = per.getOcenki();
							Iterator iter = grades.iterator();
							while (iter.hasNext()) {
								int ocena = (Integer) iter.next();
				%>
				<td bgcolor="silver" align="center"><%=ocena%></td>
				<%
					}
				%>


				<%
					}
				%>
			</tr>
			<%
				}
			%>



		</table>
</body>
</html>