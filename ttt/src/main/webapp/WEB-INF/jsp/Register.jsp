<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 --%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
<center>
	<form:form modelAttribute="user" name="thispage" action="Register.html" method="POST">

			<table align="center" border="1" cellpadding="1" cellspacing="2"
				width="50%">
				<tbody>
					<tr>
						<td width="50%">Username*:</td>
						<td valign="middle">
						<form:input style="width: 100%;" path="username"
						type="text"></form:input><br/>
					</tr>
					<tr>
						<td valign="top">Email id*:</td>
						<td valign="top"><form:input style="width: 100%;" path="email"
							type="text"></form:input></td>
					</tr>
					<tr>
						<td>Password*:</td>
						<td><form:input style="width: 100%;" path="password"
							type="password"></form:input></td>
					</tr>
				</tbody>
			</table>
			
			<input type="submit" name="submit" value="Register" />
		</form:form>
	</center>
<h2 align = "center"><a href="Login.html">Login</a></h2>

</body>
</html>