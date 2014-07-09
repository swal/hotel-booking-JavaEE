<%@page import="travelapp.PlannedTrip"%>
<%@page import="java.util.List"%>
<%@page import="travelapp.hotel.entity.Booking"%>
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

	<!-- overview of bookings -->
	<table>
		<tr>
			<td class="left_column">
				<jsp:include page="menu.html" />
			</td>
			<td class="vertical_lign"></td>
			<td class="right_column">
				<% 
		    	PlannedTrip trip = (PlannedTrip)session.getAttribute("trip");
		    	
		    	// If the user has no reservation list, create a new one
		    	if(trip == null) {
		    		trip = new PlannedTrip();
		    		session.setAttribute("trip", trip);
		    	}
		    	List<Booking> bookings = trip.getBookings();
		    	if(!bookings.isEmpty()) {
		    		%>
		    		<h3>Bookings to be finalized:</h3>
		    		<form action="/TravelApplicationJEE-war/removebooking" method="post">
		    			<%
						for(Booking b : bookings) {
		    			%>
		    				<p><input type="checkbox" name="<%= b.toString() %>" value="T"/><%= b.toString() %></p>
		    			<%
		    			}
		    			%>
		    			<br />
		    			<input type="submit" value="Remove selected booking(s)" />
		    		</form>
		    		<form action="/TravelApplicationJEE-war/finalize" method="post">
						<input type="submit" value="Finalize all bookings" />
					</form>
		    		<%
		    	} else {
		    		response.sendRedirect("index.jsp");
		    	}
				%>
			</td>
		</tr>
	</table>
</body>
</html>
