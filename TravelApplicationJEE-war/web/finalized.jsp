<%@page import="travelapp.hotel.entity.Booking"%>
<%@page import="java.util.List"%>
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
	
	<!-- Finalize bookings -->
	<table>
		<tr>
			<td class="left_column">
				<jsp:include page="menu.html" />
			</td>
			<td class="vertical_lign"></td>
			<td class="right_column">
				<% 
				List<Booking> bookings = (List<Booking>) request.getAttribute("booked");
		    	if(bookings != null && !bookings.isEmpty()) {
		    		%>
		    		<h2>The following bookings have been finalized:</h2>
		    		<ul>
		    		<%
					for(Booking b : bookings) {
		    			%>
						<li><blockquote><%= b.toString() %></blockquote></li>
						<%
		    		}
		    		%></ul><%
		    	}
				%>
			</td>
		</tr>
	</table>

</body>
</html>