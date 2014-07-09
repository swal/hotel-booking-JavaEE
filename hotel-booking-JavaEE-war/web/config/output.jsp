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

	<!-- Menu + message -->
	<table>
		<tr>
			<td class="left_column">
				<jsp:include page="menu.html" />
			</td>
			<td class="vertical_lign"></td>
			<td class="right_column">
				<%
				String message = (String)request.getAttribute("msg");
				if(message != null) {
					%>
					<h3><font color="red"><%= message %></font></h3>
					<%
				}
				%>
			</td>
		</tr>
	</table>

</body>
</html>