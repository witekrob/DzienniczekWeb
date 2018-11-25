package model;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.module.FindException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRUD {
	getConnection connect = new getConnection();
	Scanner input = new Scanner(System.in);
	int a = PersonDatabase2.a;
	Connection co = connect.connect();
	
	public CRUD() throws SQLException {
	}

	public LinkedList<Person2> getAllFromDb() throws SQLException {
		String sqlQuery = "SELECT * from uczniowie";
		PreparedStatement prepStat = co.prepareStatement(sqlQuery);
		ResultSet reSet = prepStat.executeQuery();
		LinkedList<Person2> dbList = new LinkedList<Person2>();

		String name = null;
		String surname = null;
		String pesel = null;

		while (reSet.next()) {
			List<Integer> grades = new LinkedList<Integer>();
			name = reSet.getString("name");
			surname = reSet.getString("surname");
			pesel = reSet.getString("pesel");
			grades = getGradesFromDb(pesel);

			Person2 pers = new Person2(name, surname, pesel, grades);
			dbList.add(pers);
		}
		return dbList;
	}

	public void addAllFromDbToLocal() throws SQLException {
		LinkedList<Person2> fromDb = getAllFromDb();

		for (Person2 p : fromDb) {
			PersonDatabase2.addToLocal(p);
		}
	}

	public void delete() throws SQLException {

		
		System.out.println("podaj pesel osoby do usuniÄ™cia");
		int pes = input.nextInt();
		String deleteQuery = "DELETE FROM uczniowie WHERE pesel=?;";
		PreparedStatement prepStmt = co.prepareStatement(deleteQuery);
		prepStmt.setInt(1, pes);
		int result = prepStmt.executeUpdate();
		co.close();
	}

	public void addNewOrUpdate() throws SQLException {
		System.out.println("podaj imiÄ™ studenta do dodania");
		String name = input.next();
		System.out.println("podaj nazwisko studenta do dodania");
		String surname = input.next();
		System.out.println("podaj pesel " + name + " " + surname);
		String pesel = input.next();

		Person2 newPerson = new Person2(name, surname, pesel, null);

		if (!checkByPeselInDB(newPerson)) {
			addToDb(newPerson);
		} else {
			updateInDb(newPerson);
		}
	}

	public void addToDb(Person2 pers) throws SQLException {
		Connection con = connect.connect();

		String name = pers.getName();
		String surname = pers.getSurname();
		String pesel = pers.getPesel();
		List<Integer> grades = pers.getOcenki();

		String createQuery = "INSERT into uczniowie(name,surname,pesel) VALUES (?,?,?);";
		PreparedStatement prepStat = con.prepareStatement(createQuery);
		prepStat.setString(1, name);
		prepStat.setString(2, surname);
		prepStat.setString(3, pesel);
		prepStat.executeUpdate();
		addGrades(pers, grades);
		con.close();

	}

	public void updateInDb(Person2 p) throws SQLException {
		String query = "UPDATE uczniowie SET name=?,surname=? WHERE pesel = ?;";

		String name = p.getName();
		String surname = p.getSurname();
		String pesel = p.getPesel();

		System.out.println(p.toString());

		PreparedStatement prep = co.prepareStatement(query);
		prep.setString(1, name);
		prep.setString(2, surname);
		prep.setString(3, pesel);
		prep.executeUpdate();
		co.close();
		addGrades(p, p.getOcenki());
	}

	public void updateOneRecord() throws SQLException {

		System.out.println("Podaj pesel osoby do zaktualizowania jego imienia i nazwiska w bazie ");
		String pesel = input.next();
		System.out.println("Wpisz poprawione imie osoby o numerze pesel  " + pesel);
		String name = input.next();
		System.out.println("Wpisz poprawione nazwisko osoby o numerze pesel  " + pesel);
		String surname = input.next();

		Person2 newPerson = new Person2(name, surname, pesel);

		if (checkByPeselInDB(newPerson)) {
			updateInDb(newPerson);
			addGrades(newPerson, newPerson.getOcenki());
		} else {
			System.out.println("Nie ma takiej osoby - dodajÄ™ nowÄ…");
			addToDb(newPerson);
		}

	}

	public void uploadDb() throws SQLException {
		Iterator<Person2> iter = PersonDatabase2.nowa.iterator();
		LinkedList<Person2> fromDb = getAllFromDb();
		Iterator<Person2> iterDb = fromDb.iterator();

		while (iter.hasNext()) {
			while (iterDb.hasNext()) {

				try {
					Person2 personLocal = iter.next();
					if (checkByPeselInDB(personLocal)) {
						updateInDb(personLocal);
					} else {
						System.out.println("Nie ma takiej osoby - dodajÄ™ nowÄ…");
						addToDb(personLocal);

					}
				} catch (NoSuchElementException e) {
					System.out.println("brak zmian");
					break;
				}
			}
		}
	}

	public boolean checkByPeselInDB(Person2 checkedPerson) throws SQLException {

		boolean result = false;
		String checkedPersonPesel = checkedPerson.getPesel();

		LinkedList<Person2> fromDb = getAllFromDb();
		Iterator<Person2> iterDb = fromDb.iterator();

		while (iterDb.hasNext()) {

			Person2 personDb = iterDb.next();
			String peselDb = personDb.getPesel();

			if (peselDb.equals(checkedPersonPesel)) {
				result = true;
				break;
			} else {
				result = false;
			}
		}
		return result;
	}

	public void addGrades(Person2 pers, List<Integer> grades) throws SQLException {
		String sqlAddGrade = "Insert into grades(pesel,grade) VALUES (?,?);";
		String pesel = pers.getPesel();
		System.out.println("jestem jestem dodaje, oceny");
		grades = (LinkedList<Integer>) pers.getOcenki();
		
		PreparedStatement prep = co.prepareStatement(sqlAddGrade);
		prep.setNString(1, pesel);
		if (grades != null)
			for (int i = 0; i < grades.size(); i++) {
				prep.setInt(2, grades.get(i));
				prep.executeUpdate();
			}
		co.close();
	}
	public void addGrade(Person2 p,int grade) throws SQLException{
		int idGrade = 0;
		String addGrade = "Insert into grades(pesel,grade,gradeId) values (?,?,?);";
		String pesel = p.getPesel();
		
		PreparedStatement prepStat = co.prepareStatement(addGrade);
		prepStat.setString(1, pesel);
		prepStat.setInt(2, grade);
		prepStat.setInt(3,idGrade);
		prepStat.executeUpdate();
		idGrade++;
	}

	public LinkedList<Integer> getGradesFromDb(String pesel) throws SQLException {
		
		String getGradesSQL = "Select * from dzienniczek.grades WHERE pesel=?;";
		PreparedStatement prep = co.prepareStatement(getGradesSQL);
		prep.setString(1, pesel);
		ResultSet reSet = prep.executeQuery();

		LinkedList<Integer> grades = new LinkedList<>();

		while (reSet.next()) {
			int grade = reSet.getInt("grade");
			grades.add(grade);
		}
		return grades;

	}

	public Person2 get1(String pesel) throws SQLException {
		String get1SQL = "Select * from uczniowie where pesel=?";
		PreparedStatement prep = co.prepareStatement(get1SQL);
		prep.setString(1, pesel);
		ResultSet reSet = prep.executeQuery();
		Person2 pers = null;
		String name = null;
		String surname = null;
		LinkedList<Integer> grades = new LinkedList<Integer>();
		while (reSet.next()) {
			name = reSet.getString("name");
			surname = reSet.getString("surname");
			grades = getGradesFromDb(pesel);

			pers = new Person2(name, surname, pesel, grades);

		}
		return pers;

	}
}
