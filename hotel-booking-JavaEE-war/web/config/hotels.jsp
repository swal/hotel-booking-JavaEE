<%@page import="travelapp.config.RetrieveAllHotelsServlet"%>
<%@page import="java.util.List"%>
<%@page import="travelapp.hotel.HotelInfo"%>
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
				<h2>All hotels:</h2>
				<% 
				List<HotelInfo> hotels = (List<HotelInfo>) request.getAttribute(RetrieveAllHotelsServlet.key);
				if(hotels == null || hotels.size() == 0) {
					%>
					<p>No hotels to present.</p>
					<% 
				} else {
					%><ul><%
					for(HotelInfo h : hotels) {
						%>
						<li><p><b><a href="/TravelApplicationJEE-war/config/rooms?hotel=<%= h.getHotelName() %>"><%= h.getHotelName() %></a></b></p>
						<blockquote><%= h.getAddress().toString() %></blockquote></li>
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