

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/find1byPeselServlet")
public class find1byPeselServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public find1byPeselServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	String pesel = request.getParameter("pesel");
	CRUD crud = new CRUD();
	Person2 p=null;
	try {
		p=crud.get1(pesel);
	} catch (SQLException e) {
		System.out.println("  nie    maa   __");
	}
	sendRequest(p, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
public void sendRequest(Person2 p, HttpServletResponse response ) throws IOException {
	PrintWriter print = response.getWriter();
	response.setContentType("text/html");
	response.setCharacterEncoding("UTF-8");
	print.println("<html>");

	if (p!=null) {
		
	print.println("<h1> znaleziono !!!!</h1>");
	print.println(p.toString());
	}
	else {
		print.println("Sorry , nie znalazłem");
	}
	
	print.println("</html>");
}
}
