package Servlets;

import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getFromDBServlet")
public class getFromDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public getFromDBServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
	CRUD crud = null;
	
	
	
	try {
		crud = new CRUD();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	LinkedList<Person2> fromDB = new LinkedList<Person2>();
	try {
		fromDB = crud.getAllFromDb();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	request.setAttribute("fromDB", fromDB);
	request.getRequestDispatcher("showAll.jsp").forward(request, response);

	}
	
}
