package Pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetProducts
 */
@WebServlet("/getProducts")
public class GetProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;


	Connection con = null;
	
	
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		
		super.init(config);
		con = (Connection) getServletContext().getAttribute("jdbccon");
			
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		PreparedStatement ps;
		ResultSet rs;
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		
		try
		{
			ps = con.prepareStatement("select * from product where cat_id=?");
			ps.setInt(1, c_id);
			rs = ps.executeQuery( );
			
			while(rs.next())
			{
				out.print(" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated c[atch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
