package controller;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import model.grade;
import model.Person2;
import model.Student;
import util.ConnectionProvider;

public class GradeDao {
	final static String CREATE = "INSERT into grades (pesel,grade) values (:pesel,:grade);";
	final static String READ = "SELECT gradeId,grade,pesel from grades WHERE pesel=:pesel;";
	final static String READallGrades = "SELECT * from grades";
	final static String READtry = "select name , surname, uczniowie.pesel, grade from uczniowie join grades where uczniowie.pesel=:pesel;";
	final static String READallStud = "select uczniowie.name , uczniowie.surname, uczniowie.pesel from uczniowie;";

	NamedParameterJdbcTemplate template;

	public GradeDao() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
	}

	public void addGrade(String pesel, int grade) {
		List<Person2> osoby = template.query(READallStud, new BeanPropertyRowMapper(Person2.class));
		Iterator osobyIter = osoby.iterator();
		
		while (osobyIter.hasNext()) {
			Person2 pers= (Person2)osobyIter.next();
			
			String peselFromDB = pers.getPesel();
		
			if (pesel.equals(peselFromDB)) {
			
		
		
		grade g = new grade(pesel, grade);

		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(g);
		template.update(CREATE, source);	
	}}}

	public List<Person2> showAllGradesAllStudents() {
		List<grade> allStudentsGrades = template.query(READallGrades, new BeanPropertyRowMapper(grade.class));
		List<Person2> osoby = template.query(READallStud, new BeanPropertyRowMapper(Person2.class));
		Person2 equals = new Person2();

		for (Person2 p : osoby) {
			List<Integer> ocenki = new ArrayList<Integer>();
			for (grade g : allStudentsGrades) {

				int ocena = 0;
				if (p.getPesel().equals(g.getPesel())) {
					equals = p;
					ocena = g.getGrade();
					ocenki.add(ocena);
					equals.setOcenki(ocenki);
				}
			}
		}
		return osoby;

	}

	public List<Person2> show1(String pesel) {
		SqlParameterSource source = new MapSqlParameterSource("pesel", pesel);
		
		List<Person2> lll = template.query(READtry, source,new BeanPropertyRowMapper(Person2.class));
		
		
		System.out.println(lll);
		
		List<grade> OneStudentGrades = template.query(READ, source, new BeanPropertyRowMapper(grade.class));
		UserDao ud = new UserDao();
		Person2 p = ud.searchStudent(pesel);
		int grad = 0;
		List<Person2> osoby = new ArrayList<Person2>();
		List<Integer> ocenki = new ArrayList<Integer>();
		Iterator<grade> gradeIter = OneStudentGrades.iterator();

		while (gradeIter.hasNext()) {
			grade ocena = gradeIter.next();
			grad = ocena.getGrade();
			ocenki.add(grad);
		}

		if (p == null) {
			p = new Person2("Brak takiego studenta", "", "");
		} else {
			p.setOcenki(ocenki);

		}
		osoby.add(p);

		return osoby;

	}

}
