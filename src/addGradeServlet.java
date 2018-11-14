

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addGradeServlet")
public class addGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;    public addGradeServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String pesel = request.getParameter("pesel");
	CRUD crud = null;
	try {
		crud = new CRUD();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Person2 p= new Person2();
	try {
		p = crud.get1(pesel);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String grade =request.getParameter("grade");
	
	int grade1 = Integer.parseInt(grade);
	try {
		crud.addGrade(p, grade1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	sendResponse(p,grade1, response);
	
	}
	private void sendResponse(Person2 p, int grade, HttpServletResponse response) throws IOException {
		PrintWriter print = response.getWriter();
		response.setContentType("text/html");
		print.println("<html>");
		print.print("<h1> Udane dodanie oceny : " + grade + "  dla : " + p.toString()+"</h1>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
