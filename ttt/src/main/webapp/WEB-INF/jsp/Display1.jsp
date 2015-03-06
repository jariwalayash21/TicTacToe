<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<style type="text/css">
.text-button {
	input: focus, input:active;
	outline: none;
	background-color: Transparent;
	color: white;
	cursor: pointer;
	border: 0
}
</style>
<title>Display</title>
</head>

<body>
<a href="Navigator.html">Go To Main Menu</a><br/>
	<center>
		<b>TIC TAC TOE<b />
		</b><br />
		<c:if test="${won ne null}">
${won } Won
</c:if>
		<c:if test="${Tied ne null}">
Match ${Tied}
</c:if>
		<table border="3">
			<c:forEach var="mat" begin="0" step="1" end="2">
				<tr>
					<c:forEach var="ma" begin="0" step="1" end="2">
						<c:if test="${matrix.get(mat).get(ma) eq '_'}">
							<td><a href="Display1.html?i=${mat}&j=${ma}">
									${matrix.get(mat).get(ma)} </a></td>
						</c:if>
						<c:if test="${matrix.get(mat).get(ma) ne '_'}">
							<td><c:choose>
									<c:when test="${matrix.get(mat).get(ma) eq 'O'}">
										<span style="color:#FF0000 ;">${matrix.get(mat).get(ma)}</span>
									</c:when>
								 <c:otherwise>
									<span style="color: #0000FF;">${matrix.get(mat).get(ma)}</span>
								</c:otherwise>
								</c:choose></td>
						</c:if>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<a href="SaveGame.html">Save Game</a> <br/>
		<a href="NewGame.html">New Game</a> <br />
		<a href="logout.html">Logout</a>
	</center>
</body>
</html>
