<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="http://localhost:8080/ShoppingWebApp/logincheck" method="post">
	<table>
		
			<tr>
				<td>
					<label>Enter User Id : </label>
				</td>
				<td>
					<input type="text" name="uid" />
				</td>
			</tr>
			<tr>
				<td>
					<label>Enter Password : </label>
				</td>
				<td>
					<input type="password" name="pwd" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" style="float:left;" value="LOGIN"/>
				</td>
				<td>
					<input type="reset" style="float:right;" name="RESET" />
				</td>
			</tr>		
		</table>
	</form>
</body>

</body>
</html>