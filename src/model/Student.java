package model;

import java.util.List;

public class Student {
	private String name;
	private String surname;
	private String pesel;
	private List<grade> grades;
	
	
public Student() {}

public Student(String name,String surname,String pesel,List<grade> grades) {
	this.name=name;
	this.surname=surname;
	this.pesel = pesel;
	this.grades = grades;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSurname() {
	return surname;
}

public void setSurname(String surname) {
	this.surname = surname;
}

public String getPesel() {
	return pesel;
}

public void setPesel(String pesel) {
	this.pesel = pesel;
}

public List<grade> getGrades() {
	return grades;
}

public void setGrades(List<grade> grades) {
	this.grades = grades;
}
}