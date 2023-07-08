package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserHome
 */
@WebServlet("/userHome")
public class UserHome extends HttpServlet {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Statement st;
		ResultSet rs ;
		
		RequestDispatcher rd = request.getRequestDispatcher("/header");
		rd.include(request, response);
		
		try 
		{
			st = con.createStatement();
			rs = st.executeQuery("select * from category");
			 
			while(rs.next())
			{
				out.print("<a href = getProducts?c_id="+rs.getInt(1)+" > "+rs.getString(2)+"</a><br/>");
			}
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd1 = request.getRequestDispatcher("/fotter");
		rd1.include(request, response);
		
	}

}
