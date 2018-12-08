<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="lightgreen">
<a href="/DzienniczekWeb/index.jsp">Powrót do strony głównej</a><a><br></a>

<h1> Witaj w opcji edycji bazy ocen</h1>

Wybierz co chcesz zrobić :
<form action ="	GradeServlet" method ="post">
Dodaj ocenę studentowi o podanym numerze PESEL<input type = "radio" name="menu" value="addGrade" >
<br>
Wyświetl wszystkie oceny danego studenta <input type="radio" name="menu" value="show1">
<input type="text" name="pesel" title="pesel"  />'>
<br>
Wyświetl wszystkie oceny wssystkich studentów <input type ="radio" name="menu" value ="showAllStudentsGrades">
<br>

Wpisz ocenę<input type = "number" name="grade" title="ocena" >
<br>

<input type="submit" value="działaj">
</form>

</body>
</html>