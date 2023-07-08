package Pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Headrer
 */
@WebServlet("/header")
public class Headrer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;
	
	
	
	 

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		 
			  response.getWriter().print("<h1 style='text-align: center; font-size: xx-large;'>Welcome "+getServletContext().getAttribute("username"));;
				
			  
			  out.print("<form action='logout' method='get'>");
			  out.print("<input class='btn-danger' type='submit' style='float:right' value='LogOut'/>");
				out.print("</form>");
				
				out.print("</h1><br/><br/><hr/>");
			  
	}

}
