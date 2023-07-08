package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		con = (Connection) config.getServletContext().getAttribute("jdbccon");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		  doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 
		 PrintWriter out = response.getWriter();
		 HttpSession session = request.getSession(false);
		 User user = (User) session.getAttribute("userlogin");
			String u_id = user.getU_id();
			Timestamp ts = new Timestamp(new Date().getTime());
			try 
			{
				PreparedStatement ps = con.prepareStatement("update log set logout=? where u_id=? and logout is null");
				ps.setTimestamp(1, ts);
				ps.setString(2, u_id);
				ps.executeUpdate();
			} 
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		 if(session != null)
			{
				session.invalidate();
				out.print("<p> Logged out successfully </p>");
				out.print("<br/> <a href='Login.jsp'> Login once again? </a>");
			}
		 
		
		
		
		 
		
	}

}
