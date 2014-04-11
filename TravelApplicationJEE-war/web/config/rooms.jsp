<%@page import="travelapp.hotel.entity.RoomDetails"%>
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
	
	<!-- Show all rooms -->
	<table>
		<tr>
			<td class="left_column">
				<jsp:include page="menu.html" />
			</td>
			<td class="vertical_lign"></td>
			<td class="right_column">
				<h2>All rooms of hotel <i><%= request.getParameter("hotel") %></i>:</h2>
				<% 
				List<RoomDetails> rooms = (List<RoomDetails>) request.getAttribute("rooms");
				if(rooms == null || rooms.isEmpty()) {
					%>
					<p>No rooms to present.</p>
					<% 
				} else {
					%><ul><%
					for(RoomDetails r : rooms) {
						%>
						<li><blockquote><%= r.toString() %></blockquote></li>
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