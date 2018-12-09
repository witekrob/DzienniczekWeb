package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserDao;
import model.Person2;

@WebServlet("/StudentsServlet")
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String option = request.getParameter("option");

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String pesel = request.getParameter("pesel");

		UserDao uDao = new UserDao();

		if ("add".equals(option)) {
			Person2 pers = new Person2(name, surname, pesel);
			uDao.addUser(pers);
			request.setAttribute("option", option);
			request.setAttribute("student", pers);
			request.getRequestDispatcher("studentsResult.jsp").forward(request, response);
		} else if ("search".equals(option)) {
			Person2 p = null;
			p = uDao.searchStudent(pesel);
			request.setAttribute("option", option);
			request.setAttribute("student", p);
			request.getRequestDispatcher("studentsResult.jsp").forward(request, response);
		} else if ("update".equals(option)) {
			Person2 p = new Person2(name, surname, pesel);
			uDao.updatePerson(p);
			request.setAttribute("option", option);
			request.setAttribute("student", p);
			request.getRequestDispatcher("studentsResult.jsp").forward(request, response);
		} else if ("delete".equals(option)) {
			int result = uDao.removeStudent(pesel);
			option = "Udane usunięcie";
			request.setAttribute("option", option);
			request.getRequestDispatcher("studentsResult.jsp").forward(request, response);
		} else if ("getAll".equals(option)) {
			List<Person2> list = uDao.getAll();
			if (list != null) {
				request.setAttribute("list", list);
				request.getRequestDispatcher("showAll.jsp").forward(request, response);
			}
		}
}
}
