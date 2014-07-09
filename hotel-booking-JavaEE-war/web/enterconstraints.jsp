<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>CS Travelling</title>
</head>
<body bgcolor="#ffffff">
	<!-- HEADER -->
	<jsp:include page="header.jsp" />

	<!-- Form for booking constraints -->
	<table>
		<tr>
			<td class="left_column">
				<jsp:include page="menu.html" />
			</td>
			<td class="vertical_lign"></td>
			<td class="right_column">
				<h2>Booking constraints submission form</h2>
				<center>
				<% String h = request.getParameter("hotel"); %>
				<form action="/TravelApplicationJEE-war/createbooking?hotel=<%= h %>" method="post">
					<p>Hotel: <%= h %></p>
					<p>Enter your name: 
					<input type="text" name="guest"/></p>
					<p>Enter start date (dd/mm/yyyy): 
					<input type="text" name="startdate"/><br />
					Enter end date (dd/mm/yyyy): 
					<input type="text" name="enddate"/></p>
					<p>Number of beds: 
					<input type="text" name="beds"/><br /></p>
					<p>Maximum price per night: 
					<input type="text" name="price"/><br /></p>
					<p><select name="smoking" size="1">
						<option selected value="false">Non-smoking</option>
						<option value="true">Smoking</option>
					</select></p>
    				<p><input type="submit" value="Book room" /></p>
  				</form>
  				</center>
			</td>
		</tr>
	</table>
</body>
</html>