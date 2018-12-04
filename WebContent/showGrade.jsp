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

	<%
		//Map<List<Person2>, List<grade>> mapa = (Map<List<Person2>, List<grade>>) request.getAttribute("mapa");
		
		Map<Person2, List<grade>> mapa = (Map<Person2, List<grade>>) request.getAttribute("mapa");
		Collection<List<grade>> oceny = mapa.values();
		Iterator<List<grade>> iterOcen = oceny.iterator();
		String name = null;
		String surname = null;
		Iterator<Person2> listaStudentow = mapa.keySet().iterator();
		Person2 student = null;
		grade ocena = null;
	%>
	A oto oceny studenta o numerze PESEL :

	<%
			student = listaStudentow.next();

			name = student.getName();
			surname = student.getSurname();
	%>
	<%=request.getParameter("pesel")%>
	<br> Imię :
	<%=name%>
	<br> Nazwisko :
	<%=surname%>

	<br>

	<br>
	<table>
		<table border="2">
			<tr>
				<th>Pesel</th>
				<th>Ocena</th>
				<th>id</th>
			</tr>
			<%
				while (iterOcen.hasNext()) {
					List<grade> ocenyy = iterOcen.next();
					for (grade g : ocenyy) {
			%>
			<tr align="center">
				<td><%=g.getPesel()%>
				<td><%=g.getGrade()%></td>
				<td><%=g.getGradeId()%></td>
				<%
					}
					}
				%>
			
		</table>
</body>
</html>