package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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


@WebServlet("/logincheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;
	
	
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		
		super.init(config);
		con = (Connection) getServletContext().getAttribute("jdbccon");
			
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 String uid = request.getParameter("uid");
		 String pwd = request.getParameter("pwd");
		 PreparedStatement ps=null ;
		 ResultSet rs=null;
		 try 
		 {
			ps = con.prepareStatement("select * from users where u_id = ? and password = ?");
			ps.setString(1, uid);
			ps.setString(2, pwd);
			  rs = ps.executeQuery();
			
			if(rs.next())
			{
				getServletContext().setAttribute("username", rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
				 RequestDispatcher dispatcher = request.getRequestDispatcher("/userHome");

			        // Forward the request and response objects to the target servlet
			        dispatcher.forward(request, response);
//				out.print("Success");
			}
			else
			{
				out.print("<h2>try again</h2>");
				response.sendRedirect("/ShoppingWebApp/Login.jsp");
			}
				
		 }
		 catch (SQLException e) 
		 {
			e.printStackTrace();
		 
		 }
		 finally
		 {
			 try 
			 {
				rs.close();
				 ps.close();
			 } 
			 catch (SQLException e) 
			 {
				e.printStackTrace();
			 }
			
		 }
		 
	}

}
