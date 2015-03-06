<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Join Game</title>
</head>
<body>
Joined Game Hosted by Player ${hostUser }. Waiting for ${hostUser } move..<br/>
<a href="Navigator.html">Go To Main Menu</a><br/>
<center>
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
</center>
<a href="logout.html">Logout</a>
</body>
</html>