<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game Details</title>
</head>
<body>
Number of Games Completed: ${noOffinishedGamesByUser}.<br/>
Number of 1-player Games Completed: ${noOfAIGamesPlayedByUser}.<br/>
Number of 2-player Games Completed: ${noOfHumanGamesPlayedByUser}.<br/>
Win Rate Against AI: ${winRateAgainstAI}%.<br/>
Win Rate Against Human: ${winRateAgainstHuman}%.<br/>
<br/>
You Played Games Details.<br/>
<c:forEach items="${gamePlayedThisMonth }" var="game" varStatus="g">
| Username: ${opponents_usernames[g.index]} |
Length: ${gamePlayLength[g.index]} | 
Outcome: ${results[g.index]} | <br/>
</c:forEach>
<a href="Navigator.html">Go To Main Menu</a><br/>
<a href="logout.html">Logout</a><br/>
</body>
</html>