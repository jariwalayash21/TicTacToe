<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Join</title>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			url : "h.json",
			cache : false,
			success : function(data) {
				data.ids.forEach(function(id) {
					window.location = "jGame.html?id=" + data.ids[0];
				});
			},
		});
		update();
	});
	function update() {
		$.ajax({
			url : "h.deferred.json",
			cache : false,
			success : function(data) {
				var no_of_wait = $('#no_of_wait').val();
				if (no_of_wait == 1) {
					data.forEach(function(id) {
						window.location = "jGame.html?id=" + data[0];
					});
				} else {
					$('#no_of_wait').val(no_of_wait - 1);
				}
				update();
			},
		});
	}
</script>
</head>
<body>
	<input type="hidden" id="no_of_wait" value="${no_of_wait }" /> Waiting
	for a Player to Host a Game..
	<br />
	<a href="Navigator.html">Go To Main Menu</a>
	<br />
	<a href="logout.html">Logout</a>
</body>
</html>