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
<title>CS Travel Application</title>
</head>
<body bgcolor="#ffffff">
	<!-- HEADER -->
	<jsp:include page="header.jsp" />

	<!-- Overview -->
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
		    		//session.setMaxInactiveInterval(120);
		    	}
		    	List<Booking> bookings = trip.getBookings();
		    	if(!bookings.isEmpty()) {
		    		%>
		    		<h2>Tentative bookings</h2>
		    		<ul>
		    		<%
					for(Booking b : bookings) {
		    			%>
						<li><blockquote><%= b.toString() %></blockquote></li>
						<%
		    		}
		    		%></ul><%
		    	} else {
		    		%><center><h1>Welcome!</h1></center><%
		    	}
				%>
			</td>
		</tr>
	</table>
	
</body>
</html>
