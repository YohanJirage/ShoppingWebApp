<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% Connection con = (Connection) application.getAttribute("jdbccon");  %>
	<%
	List<Integer> products = (List<Integer>) session.getAttribute("cart");
	
	if(products == null)
	{
	%>
		<h1>Cart is Empty</h1><p>Add some products</p><br/>
		<h2><a href='userHome'>Go Back To add More</a></h2>
	<%
	}
	else
	{
		if(!products.isEmpty())
		{
			%><table border='1'style='border-collapse: collapse;' ><%
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
						%><tr> 
							<td><%  out.print(++sr) ; %> </td>
							<td><% out.print(rs.getString(2)) ; %></td>
							<td><% out.print(rs.getFloat(4)) ; %></td>
							<td><a href= 'remove?pid=<%  out.print(i) ;  %> '>remove</a></td>
						</tr><%
						
						
					total+=rs.getFloat(4);
					}
				}
				
				%><tr>
				 
					<td colspan='2'> Total Price </td>
					<td>"+total+"</td>
					</tr>
					</table> <%  
			
				session.setAttribute("totalprice", total);
				
				
			} catch (Exception e) 
			{
				// TODO: handle exception
			 
				response.sendRedirect("Error.jsp");
			}
		}
		else
		{
			%><h1>Cart is Empty</h1><p>Add some products</p><br/>
			<h2><a href='userHome'>Go Back To add More</a> </h2> <% 
		}
		%> <h2><a href='userHome'>Go Back To add More</a> </h2> 
	 <h3><a href = 'confirmOrder'>Click To Order</a></h3> <%
	}
	
	RequestDispatcher rd1 = request.getRequestDispatcher("/fotter");
	rd1.include(request, response);
	

	
	%>

</body>
</html>