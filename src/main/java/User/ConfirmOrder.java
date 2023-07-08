package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.cert.CertPathBuilderResult;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

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
 * Servlet implementation class ConfirmOrder
 */
@WebServlet("/confirmOrder")
public class ConfirmOrder extends HttpServlet {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher rd = request.getRequestDispatcher("/header");
		rd.include(request, response);
		
		PrintWriter out =  response.getWriter();
		HttpSession session = request.getSession();
		
		User u = (User) session.getAttribute("userlogin");
		float total = (float) session.getAttribute("totalprice");
		
		List<Integer> products = (List<Integer>) session.getAttribute("cart");
		
		Timestamp ts = new Timestamp(new Date().getTime());
		int n=0;
		PreparedStatement ps;
		ResultSet rs ;
		try {
			ps = con.prepareStatement("insert into shopping (user_id,shoppingDate,totalprice) values(?,?,?)");
			ps.setString(1, u.getU_id());
			ps.setTimestamp(2, ts);
			ps.setFloat(3, total);
			
			n = ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect("Error.jsp");
		}
		
		if(n == 1)
		{
			products.clear();
			session.removeAttribute("cart");
			
			
			
			out.print("<h1>ORDER IS CONFIRMED</h1>");
			out.print("<p> Details</p>");
			out.print("<p>User id : "+u.getU_id()+" </p><br/>");
			out.print("<p>Total Amount : "+total+" </p><br/>");
	//		out.print("<p>Date : "+u.getU_id()+" </p><br/>");
			out.print("<p> You bill will be emailed at "+ u.getEmail()+"</p>");
			out.print("<p> You will receive message on"+ u.getContact() +" before order delivery </p>");
			out.print("<h2><a href='userHome'>Go Back To add More</a> </h2>");
			
			out.print("<br/> <br/><h1> <a href='logout'> Logout <a/></h1>");
		}
		out.print("<h1>Please try Agian</h1>");
		out.print("<h2><a href='userHome'>Go Back To add More</a> </h2>");
		
		
		RequestDispatcher rd1 = request.getRequestDispatcher("/fotter");
		rd1.include(request, response);
		
		
	}

}
