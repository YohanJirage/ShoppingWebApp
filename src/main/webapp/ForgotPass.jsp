<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Forgot Password</h3>
	<form action="http://localhost:8080/ShoppingWebApp/forgot" method="post">
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
					<label>Enter Email: </label>
				</td>
				<td>
					<input type="email" name="email" />
				</td>
			</tr>
			<tr>
				<td>
					<label>Enter New Password: </label>
				</td>
				<td>
					<input type="password" name="pwd1" />
				</td>
			</tr>
			<tr>
				<td>
					<label>Confirm Password: </label>
				</td>
				<td>
					<input type="password" name="pwd2" />
				</td>
			</tr>

			<tr>
				<td>
					<input type="submit" style="float:left;" value="Change Password"/>
				</td>
				<td>
					<input type="reset" style="float:right;" name="RESET" />
				</td>
			</tr>		
		</table>
	</form>

</body>
</html>