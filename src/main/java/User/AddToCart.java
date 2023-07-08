package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con ;
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		con = (Connection) config.getServletContext().getAttribute("jdbccon");
		
	}
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	 
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher rd = request.getRequestDispatcher("/header");
		rd.include(request, response);
		
		PrintWriter out  = response.getWriter();
		int pid = Integer.parseInt(request.getParameter("products"));  // producst is a name of select box
		HttpSession session = request.getSession();
		List <Integer> products = (List<Integer>)session.getAttribute("cart");
		if(products == null)
		{
			products = new ArrayList<>();
		}
		products.add(pid);
		session.setAttribute("cart", products);
		
		out.print("<h2>Product with Product Id : "+pid+" is Added In the cart </h2>");
		out.print("<h2>"+products.size()+" Items are added in to the Cart<h2>");
		
		
		out.print("<h2><a href='userHome'>Go Back To add More</a> </h2>");
		out.print("<h2><a href='ViewCart.jsp'>Viwe Added Products in cart</a> </h2>");
		
		
		RequestDispatcher rd1 = request.getRequestDispatcher("/fotter");
		rd1.include(request, response);
		

	}

}
