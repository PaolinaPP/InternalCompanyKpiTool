<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Logout</title>
</head>
<body>
	<div id="box1">
		<div id="login">
			<%
				String userName = null;
			String sessionID = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("user"))
				userName = cookie.getValue();
				}
			}
			%>
			<h3 style="color: black;">Hello!</h3>
			<h3 style="color: black;">Do you really want to logout?</h3>
			<br>
			<form action="logout" method="post">
				<input type="submit" value="logout" class="submit_button">
			</form>
		</div>
	</div>
</body>
</html>