package controller;
import model.Person2;
import model.PersonDatabase2;
import util.ConnectionProvider;

import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class UserDao {
NamedParameterJdbcTemplate template;
private final static String CREATE = 
"INSERT INTO uczniowie (pesel, name, surname) VALUES(:pesel, :name, :surname);";
private final static String READ = 
"SELECT pesel, name, surname FROM uczniowie WHERE pesel = :pesel;";
private final static String UPDATE = 
"UPDATE uczniowie SET pesel=:pesel, name=:name, surname=:surname WHERE pesel = :pesel;";
private final static String DELETE = 
"DELETE FROM uczniowie WHERE pesel=:pesel;";
private final static String GETALL = "SELECT pesel,name,surname from uczniowie;";


public UserDao() {
	template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
	
}
public void addUser(Person2 p) {

BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(p);

template.update(CREATE, source);

}
public Person2 searchStudent(String pesel) {
	Person2 found=null;
	SqlParameterSource source = new MapSqlParameterSource("pesel",pesel);
	List<Person2> PeopleList = template.query(READ, source, BeanPropertyRowMapper.newInstance(Person2.class));
	
	if (PeopleList!=null) {

	for (Person2 p: PeopleList) {
		System.out.println(p.toString());
		found = p;
	}
	}
	else {
		System.out.println("nie znalazłem");
	}
	return found;
}
public void updatePerson (Person2 p) {
	
	BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(p);
	template.update(UPDATE, source);
}
public int removeStudent (String pesel) {
int result = 0;

SqlParameterSource source = new MapSqlParameterSource("pesel",pesel);

result = template.update(DELETE,source);

return result;
}
public List<Person2> getAll (){
	
	List<Person2> PeopleList = template.query(GETALL, BeanPropertyRowMapper.newInstance(Person2.class));
	for (Person2 p:PeopleList) {
		System.out.println(p.toString());
	}
	
	return PeopleList;
}
}
