package User;

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

import com.mysql.cj.xdevapi.Collection;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/forgot")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		con = (Connection)getServletContext().getAttribute("jdbccon");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/header");
		rd.include(request, response);
		
		String pwd1 = request.getParameter("pwd1");
		String pwd2 = request.getParameter("pwd2");
		if(pwd1.equals(pwd2))
		{
			PrintWriter out = response.getWriter();
			String uid = request.getParameter("uid");
			String email = request.getParameter("email");
			PreparedStatement ps;
			try 
			{
				ps = con.prepareStatement("update users set password=? where u_id = ? and email = ?");
				ps.setString(1, pwd1);
				ps.setString(2, uid);
				ps.setString(3, email);
				int n = ps.executeUpdate();
				if(n==1)
				{
					out.print("<h3>Password is Changed</h3><a href='http://localhost:8080/ShoppingWebApp/Login.jsp'>LogIn</a><br/>");
					
				}
				
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				response.sendRedirect("Error.jsp");
			
			}
			
			
			
		}
		else
			response.sendRedirect("/ShoppingWebApp/ForgotPass.jsp");
		
	}

}
