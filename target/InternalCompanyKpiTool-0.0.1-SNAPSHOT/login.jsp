<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="header">Internal KPI Tool</div>
	<div class="row">
		<div class="col-2 col-m-3 menu">
			<ul>
				<li onclick="parent.location='index.jsp'">Home</li>
				<li onclick="parent.location='login.jsp'">Login</li>

			</ul>
		</div>
		<div class="col-7 col-m-9">
			<h3 class="text-center">Login</h3>
			<hr>
			<form method="post" action="login">
				<div class="row">
					<input id="username" class="input" name="username"
						placeholder="Email" type="text" value="" size="50" height="15px" /><br />
					<br />
				</div>
				<div class="row">
					<input id="password" class="input" name="password"
						placeholder="Password" type="password" value="" size="50"
						height="15px" /><br />
				</div>
				<%
					if (null != request.getAttribute("error")) {
					String error = (String) request.getAttribute("error");
					if (error == null) {
						error = "";
					}
				%>
				<p style='color: red; font-size: 90%;'><%=error%></p>
				<%
					}
				%><br/>
				<input class="submit_button" name="signUpButton" type="submit"
					value="Sign In" />
			</form>
		</div>
		<div class="col-3 col-m-12"></div>
	</div>
	<div class="footer">Internal KPI Tool | All rights reserved. 2021</div>
</body>
</html>