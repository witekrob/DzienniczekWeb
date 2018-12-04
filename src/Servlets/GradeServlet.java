package Servlets;

import model.Person2;
import model.grade;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.GradeDao;
import controller.UserDao;

@WebServlet("/GradeServlet")
public class GradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GradeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pesel = request.getParameter("pesel");

		GradeDao gDao = new GradeDao();
		String option = request.getParameter("menu");
		System.out.println(option);

		if ("addGrade".equals(option)) {
			int grade = Integer.parseInt(request.getParameter("grade"));
			gDao.addGrade(pesel, grade);

			List<grade> grades = gDao.show1StudentGrades(pesel);
			Person2 student = new Person2();
			UserDao userDao = new UserDao();
			student = userDao.searchStudent(pesel);
			request.setAttribute("grades", grades);
			request.setAttribute("student", student);

			request.getRequestDispatcher("/showGrade.jsp").forward(request, response);

		} else if ("show1".equals(option)) {
			Map<Person2,List<grade>> mapa  = gDao.show1(pesel);
			List<grade> grades= gDao.show1StudentGrades(pesel);
			UserDao userDao = new UserDao();
			//Person2 student = userDao.searchStudent(pesel);
			//request.setAttribute("grades", grades);
			//request.setAttribute("student", student);
			request.setAttribute("mapa", mapa);

			request.getRequestDispatcher("/showGrade.jsp").forward(request, response);
		}
		/*else if ("showAllStudentsGrades".equals(option)) {
			List<grade> grades = gDao.showAllGradeAllStudents();
			Iterator<grade> gradIter =  grades.iterator();
			List<Person2> studentsList = new LinkedList<Person2>();
		Person2 student = new Person2();
			while(gradIter.hasNext()) {
				studentsList = new LinkedList<Person2>();
				grade g = gradIter.next();
				String pesl = g.getPesel();
				UserDao u = new UserDao();
				student = u.searchStudent(pesl);
				studentsList.add(student);
			}
			*/
		else if ("showAllStudentsGrades".equals(option)) {
			Map<List<Person2>, List<grade>> mapa  = gDao.showAllGradeAllStudents();
			request.setAttribute("mapa", mapa);
			request.getRequestDispatcher("/showGrade.jsp").forward(request, response);
			
		}
	}
}