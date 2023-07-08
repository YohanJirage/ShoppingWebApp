package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.*;


@WebServlet("/logincheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con = null;
	int failed ;
	
	
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
	
		HttpSession session = request.getSession();
		session.getAttribute("loginTime");
		Timestamp ts = new Timestamp(new Date().getTime());
		session.setAttribute("loginTime", ts);
		String user_id="";
		
		
		
		
		response.setContentType("text/html");
		int failed=0;
		Cookie [] allcoo = request.getCookies();
		if(allcoo != null)
		{
			for(Cookie e : allcoo)
			{
				if(e.getName().equals("failed"))
				{
					failed = Integer.parseInt(e.getValue());
					e.setValue((failed+1)+"");
					response.addCookie(e);
				}
			}
		}
		
		
			
			
			failed++;
			Cookie c = new Cookie("failed",failed+"");
			response.addCookie(c);
		
		
		PrintWriter out = response.getWriter();
		 String uid = request.getParameter("uid");
		 String pwd = request.getParameter("pwd");
		 PreparedStatement ps=null ;
		 ResultSet rs=null;
		 try 
		 {
			ps = con.prepareStatement("select * from user where u_id = ? and password = ?");
			ps.setString(1, uid);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			
			 
			  
			if(rs.next())
			{
				 user_id = rs.getString(1);
				User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				session.setAttribute("userlogin", user) ;
				
				for(Cookie e: allcoo)
				{
					if(e.getName().equals("failed"))
					{
						e.setMaxAge(0);
						response.addCookie(e);
					}
				}
				
				
				ps = con.prepareStatement("insert into log (u_id,login) values(?,?)");
				ps.setString(1, user_id );
				ps.setTimestamp(2, ts);
				
				ps.executeUpdate();
				getServletContext().setAttribute("username", rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
				 RequestDispatcher dispatcher = request.getRequestDispatcher("/userHome");

			        // Forward the request and response objects to the target servlet
			        dispatcher.forward(request, response);
//				out.print("Success");
			}
			else
			{
				
				
					
				
				Cookie msg = new Cookie("msg", "Invalid_uid_and_pwd");
				if(failed > 3)
				{
					
					response.sendRedirect("/ShoppingWebApp/ForgotPass.jsp");
				}
				else
				{
					response.addCookie(msg);
					response.sendRedirect("/ShoppingWebApp/Login.jsp");
				}
			}
			
		
				
				
		 }
		 catch (SQLException e) 
		 {
			 response.sendRedirect("Error.jsp");
			
		 
		 }
		 finally
		 {
			 try 
			 {
				  
				 ps.close();
			 } 
			 catch (SQLException e) 
			 {
				 response.sendRedirect("Error.jsp");
			 }
			
		 }
		 
	}

}
