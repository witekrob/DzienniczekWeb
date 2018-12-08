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
	final static String READ1 = "select name , surname, grade,gradeId from uczniowie join grades on uczniowie.pesel=grades.pesel where uczniowie.pesel=:pesel;";
	final static String READall = "SELECT * from grades";
	final static String READtry = "select name , surname, uczniowie.pesel, grade from uczniowie join grades;";
	final static String READstud = "select uczniowie.name , uczniowie.surname, uczniowie.pesel from uczniowie;";

	NamedParameterJdbcTemplate template;

	public GradeDao() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
	}

	public void addGrade(String pesel, int grade) {

		grade g = new grade(pesel, grade);

		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(g);

		template.update(CREATE, source);

	}

	public List<grade> show1StudentGrades(String pesel) {

		SqlParameterSource source = new MapSqlParameterSource("pesel", pesel);
		List<grade> ga = template.query(READ, source, new BeanPropertyRowMapper(grade.class));

		return ga;
	}

	/*
	 * public List<grade> showAllGradeAllStudents() {
	 * 
	 * List<grade> allStudentsGrades = template.query(READall, new
	 * BeanPropertyRowMapper(grade.class)); return allStudentsGrades; }
	 */
	//public Map<List<Person2>, List<grade>> showAllGradesAllStudents() {
	public List<Person2> showAllGradesAllStudents(){	
	List<grade> allStudentsGrades = template.query(READall, new BeanPropertyRowMapper(grade.class));
		List<Person2> osoby = template.query(READstud, new BeanPropertyRowMapper(Person2.class));
		Person2 equals = new Person2();

		for (Person2 p : osoby) {
			List<Integer> ocenki = new ArrayList<Integer>();
			for (grade g : allStudentsGrades) {
				
				int ocena = 0;
				if (p.getPesel().equals(g.getPesel())) {
					
					equals = p;

					ocena = g.getGrade();
					System.out.println("ocena : " + ocena);
					
					ocenki.add(ocena);
					System.out.println("ocenki : " +ocenki); 

					equals.setOcenki(ocenki);
				}

			}
		}

		System.out.println(osoby.toString());

		return osoby;
		
		//Map<List<Person2>, List<grade>> mapa = new HashMap<List<Person2>, List<grade>>();
		//mapa.put(osoby, allStudentsGrades);

		//return mapa;

	}

	/*
	 * public Map<Person2, List<grade>> show1(String pesel) { SqlParameterSource
	 * source = new MapSqlParameterSource("pesel", pesel); List<grade> ga =
	 * template.query(READ, source, new BeanPropertyRowMapper(grade.class)); UserDao
	 * ud = new UserDao(); Person2 p = ud.searchStudent(pesel); int grad = 0;
	 * List<Person2> osoby = template.query(READtry, new
	 * BeanPropertyRowMapper(Person2.class));
	 * 
	 * Map<Person2, List<grade>> mapa = new HashMap<Person2, List<grade>>();
	 * mapa.put(p, ga); System.out.println(mapa.values()); return mapa; }
	 */
	public Map<List<Person2>, List<grade>> show1(String pesel) {
		SqlParameterSource source = new MapSqlParameterSource("pesel", pesel);
		List<grade> ga = template.query(READ, source, new BeanPropertyRowMapper(grade.class));
		UserDao ud = new UserDao();
		Person2 p = ud.searchStudent(pesel);
		int grad = 0;
		List<Person2> osoby = new ArrayList<Person2>();
		osoby.add(p);
		Map<List<Person2>, List<grade>> mapa = new HashMap<List<Person2>, List<grade>>();
		mapa.put(osoby, ga);
		return mapa;
	}

}
