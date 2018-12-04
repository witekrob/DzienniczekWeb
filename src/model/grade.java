package model;

public class grade {

	
private String pesel;
private int grade;
private int gradeId;

public grade() {}
public grade(String pesel, int grade) {
	this.pesel=pesel;
	this.grade=grade;	
}
public grade (int gradeId,String pesel, int grade) {
	this.pesel=pesel;
	this.grade=grade;
	this.gradeId=gradeId;
}

public String getPesel() {
	return pesel;
}
public void setPesel(String pesel) {
	this.pesel = pesel;
}
public int getGrade() {
	return grade;
}
public void setGrade(int grade) {
	this.grade = grade;
}
	

@Override
public String toString () {
	return pesel + " " + grade;
}
public int getGradeId() {
	return gradeId;
}
public void setGradeId(int gradeId) {
	this.gradeId = gradeId;
}
}
