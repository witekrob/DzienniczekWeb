

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
	CRUD  crud = new CRUD();
	
	LinkedList<Person2> fromDB = new LinkedList<Person2>();
	try {
		fromDB = crud.getAllFromDb();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	sendToResponse(fromDB, response);
	
	
	}
public void sendToResponse(LinkedList<Person2> lista, HttpServletResponse response) throws IOException {
	PrintWriter print =  response.getWriter();
	response.setContentType("text/html");
	print.println("<html>");
	
	for (Person2 pers:lista) {
		String info = pers.toString();
	print.println(info);	
	print.println("<br>");
	}
	print.println("</html>");
	
	
	
}
}