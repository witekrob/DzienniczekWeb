<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Witaj , jeśli chcesz dodać nowego ucznia, podaj poniżej jego
		dane</h1>
	<form action="AddToDbServlet">
		<input type="text" name="name" value="podaj imie" required="required">
		<input type="text" name="surname" value="podaj nazwisko" required="required"> 
		<input type="text" name="pesel" value="podaj pesel" required="required"> 
		<input type="submit" value="wyslij">

	</form>

	<h1>jeśli chcesz pobrać listę wsystkich uczniów naciśnij przycisk</h1>
	<form action="getFromDBServlet">
		<input type="submit" value="pobierz">



	</form>

	<form action="find1byPeselServlet">
		<input type="text" name="pesel" value="Podaj pesel szukanego studenta">
		<input type="submit" value="szukaj">

	</form>
</body>
</html>