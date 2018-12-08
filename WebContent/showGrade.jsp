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
		Map<List<Person2>, List<grade>> mapa = (Map<List<Person2>, List<grade>>) request.getAttribute("mapa");
		Collection<List<grade>> oceny = mapa.values();
		Iterator<List<grade>> iterOcen = oceny.iterator();
		Collection<List<Person2>> osoby = mapa.keySet();
		Iterator<List<Person2>> iterOsob = osoby.iterator();

		List<Person2> studentsList = new ArrayList<Person2>();
		while (iterOsob.hasNext()) {
			studentsList = iterOsob.next();
		}
		Person2 pers = new Person2();
		for (Person2 per : studentsList) {
			pers=per;
		}
	%>
	A oto oceny studenta o numerze PESEL :
	<%=pers.getPesel()%>

	<br> Imię :
	<%=pers.getName()%>
	<br> Nazwisko :
	<%=pers.getSurname()%>
	<%
		
	%>
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
				List<grade> gradesList = new ArrayList<>();
				while (iterOcen.hasNext()) {
					gradesList = iterOcen.next();
				}
				for (grade g : gradesList) {
			%>
			<tr align="center">
				<td><%=g.getPesel()%>
				<td><%=g.getGrade()%></td>
				<td><%=g.getGradeId()%></td>
				<%
					}
				%>
			
		</table>
</body>
</html>