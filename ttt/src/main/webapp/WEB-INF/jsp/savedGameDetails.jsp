<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Saved Games</title>
</head>
<body>
<br/>
You Saved Games Details.<br/>
<c:forEach items="${savedGamesByUser }" var="game" varStatus="g">
|Game Saved On ${game.savedTime } |
<a href="resumeGame.html?gameID=${game.id }">Resume Game</a> | <br/>
</c:forEach>
<a href="Navigator.html">Go To Main Menu</a><br/>
<a href="logout.html">Logout</a><br/>
</body>
</html>