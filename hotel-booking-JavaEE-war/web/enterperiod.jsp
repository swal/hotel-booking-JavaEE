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

	<!-- Form for period of availability -->
	<table>
		<tr>
			<td class="left_column">
				<jsp:include page="menu.html" />
			</td>
			<td class="vertical_lign"></td>
			<td class="right_column">
				<h2>Search for available hotels</h2>
				<center>
				<form action="/TravelApplicationJEE-war/checkavailability" method="post">
					<p>Enter start date (dd/mm/yyyy): 
					<input type="text" name="startdate"/><br />
					Enter end date (dd/mm/yyyy): 
					<input type="text" name="enddate"/></p>
    				<p><input type="submit" value="Search available hotels" /></p>
  				</form>
  				</center>
			</td>
		</tr>
	</table>
</body>
</html>