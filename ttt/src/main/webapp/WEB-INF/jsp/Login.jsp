<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h2 align = "center">Welcome to Tic Tac Toe !!!!</h2><br><br>
<body>
<center>

 <form:form modelAttribute="user" name="thispage"  action="Login.html" method = "post">
<table>
  <tr>
    <td>Username*</td>
    <td><form:input path="username" /><span style="color: red;"><form:errors path="username" /></span></td>
  </tr>
  <tr>
    <td>Password*</td>
    <td><form:password path="password" /><span style="color: red;"><form:errors path="password" /></span></td>
  </tr>
  <tr>
    <td colspan="2"><input type="submit" name="add" value="Login" /></td>
  </tr>
</table>

</form:form>
<h2 align = "center">Not Registerd? <a href="Register.html">Register</a></h2>
</center>
</body>
</html>