package Servlets;

import model.Person2;
import model.grade;

import java.io.IOException;
import java.util.ArrayList;
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

			List<Person2> lista = gDao.show1(pesel);
			System.out.println("listaaa");
			request.setAttribute("lista", lista);
			request.setAttribute("option", option);
			request.getRequestDispatcher("/showGrade.jsp").forward(request, response);

		} else if ("show1".equals(option)) {
			List<Person2> lista = gDao.show1(pesel);
			System.out.println("listaaa");
			request.setAttribute("lista", lista);

			request.setAttribute("option", option);
			request.getRequestDispatcher("/showGrade.jsp").forward(request, response);
		}

		else if ("showAllStudentsGrades".equals(option)) {
			List<Person2> lista = gDao.showAllGradesAllStudents();
			request.setAttribute("lista", lista);
			request.setAttribute("option", option);
			request.getRequestDispatcher("/showGrade.jsp").forward(request, response);

		}
	}
}