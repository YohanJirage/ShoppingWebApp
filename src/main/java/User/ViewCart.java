package User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/viewCart")
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con =(Connection) getServletContext().getAttribute("jdbccon");;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher rd = request.getRequestDispatcher("/header");
		rd.include(request, response);
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		List<Integer> products = (List<Integer>) session.getAttribute("cart");
		if(products == null)
		{
			out.print("<h1>Cart is Empty</h1><p>Add some products</p><br/>");
					out.print("<h2><a href='userHome'>Go Back To add More</a> </h2>");
		}
		else
		{
			if(!products.isEmpty())
			{
				out.print("<table border='1'style='border-collapse: collapse;' >");
				PreparedStatement ps;
				ResultSet rs ;
				int sr = 0;
				float total = 0.0f;
				try 
				{
					
					for(int i : products)
					{
						ps = con.prepareStatement("select * from product where p_id = ?");
						ps.setInt(1,i);
						rs = ps.executeQuery();
						if(rs.next())
						{
							out.print("<tr>");
								out.print("<td>"+ ++sr +"</td>");
								out.print("<td>"+ rs.getString(2) +"</td>");
								out.print("<td>"+ rs.getFloat(4) +"</td>");
								out.print("<td><a href='remove?pid="+ i +"'>remove</a></td>");
								total+=rs.getFloat(4);
							out.print("</tr>") ;
						}
					}
					out.print("<tr>");
					 
						out.print("<td colspan='2'> Total Price </td>");
						out.print("<td>"+total+"</td>");
						 
					out.print("</tr>") ;
					
					
					out.print("</table>") ;
				
					session.setAttribute("totalprice", total);
					
					
				} catch (Exception e) 
				{
					response.sendRedirect("Error.jsp");
					// TODO: handle exception
				}
			}
			else
			{
				out.print("<h1>Cart is Empty</h1><p>Add some products</p><br/>");
				out.print("<h2><a href='userHome'>Go Back To add More</a> </h2>");
			}
			out.print("<h2><a href='userHome'>Go Back To add More</a> </h2>");
			out.print("<h3><a href = 'confirmOrder'>Click To Order</a></h3>");
		}
		
		RequestDispatcher rd1 = request.getRequestDispatcher("/fotter");
		rd1.include(request, response);
		
	}

}
