package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RemoveFromCart
 */
@WebServlet("/remove")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		List<Integer> products = (List<Integer>) session.getAttribute("cart");
		int pid = Integer.parseInt(request.getParameter("pid"));
		/*int index = 0;
		for(int i : products)
		{
			
			if(i == pid)
			{
				products.remove(index);
			}
			index++;
		}*/
		products.remove(Integer.valueOf(pid));
		session.setAttribute("cart", products);
//		out.print("<h1>Product is removed</h1>");
//		out.print("<form action='viewCart' method='get'>");
//		out.print("<input type='submit' value='View Cart'>");
//		out.print("</form>");
//		
		response.sendRedirect("ViewCart.jsp");
		
	}

}
