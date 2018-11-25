<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	witaj.

	<form action="masterServlet" method="post">
		Wpisz Imię : <input type="text" title="name" name="name"> <br>
		Wpisz nazwisko : <input type="text" title="surname" name="surname">
		<br> Wpisz numer pesel : <input type="text" title="pesel"
			name="pesel"> 
		<br> Wybierz co chcesz zrobić 
		<br>
		Dodaj nowego : <input type="radio"
			name="option" value="add"> <br> Usuń ucznia po numerze pesel: <input
			type="radio" name="option" value="delete"> <br> Szukaj
		po numerze pesel : <input type="radio" name="option" value="search">
		<br> Uaktualnij dane : <input type="radio" name="option"
			value="update">
			<br>
			Wyświetl wszystkich studentów : <input type="radio"
			name="option" value="getAll"> <br> 
			<br> <input type="submit" value="wyslij">
	</form>

</body>
</html>