package Servlets;

import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddToDbServlet")
public class AddToDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddToDbServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String pesel = request.getParameter("pesel");
		Person2 pers = new Person2();
		pers.setName(name);
		pers.setSurname(surname);
		pers.setPesel(pesel);
		pers.setOcenki(null);

		try {
			addToDb(pers);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sendResponse(pers, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void addToDb(Person2 p) throws SQLException {
		CRUD cr = new CRUD();
		if (!cr.checkByPeselInDB(p)) {
			cr.addToDb(p);
		} else {
			cr.updateInDb(p);
		}

	}

	public void sendResponse(Person2 p, HttpServletResponse response) throws IOException {
		PrintWriter print = response.getWriter();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		print.println("<html>");
		print.println("<h1> udane dodanie rekordu do bazy </h1>");
		print.println("<div>" + p.toString() + "</div>");
		print.println("</html>");

	}

}
