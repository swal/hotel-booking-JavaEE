<%@page import="travelapp.hotel.entity.Booking"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<title>CS Travelling</title>
</head>
<body bgcolor="#ffffff">
	<!-- HEADER -->
	<jsp:include page="header.html" />
	
	<!-- Show all bookings -->
	<table>
		<tr>
			<td class="left_column">
				<jsp:include page="menu.html" />
			</td>
			<td class="vertical_lign"></td>
			<td class="right_column">
				<h2>All bookings:</h2>
				<% 
				List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
				if(bookings == null || bookings.isEmpty()) {
					%>
					<p>No bookings to present.</p>
					<% 
				} else {
					%><ul><%
					for(Booking b : bookings) {
						%>
						<li><blockquote><%= b.toString() %></blockquote></li>
						<%
					}
					%></ul><%
				}
				%>
				<form action="/TravelApplicationJEE-war/config/cleanbookings" method="get">
						<input type="submit" value="Remove all bookings" />
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
