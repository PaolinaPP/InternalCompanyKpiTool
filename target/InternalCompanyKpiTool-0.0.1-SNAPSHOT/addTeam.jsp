<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Team</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.io.*"%>
</head>
<body>
	<div class="header">Internal KPI Tool</div>
	<div class="row">
		<div class="col-2 col-m-3 menu">
			<ul>
				<li onclick="parent.location='admin.jsp'">Profile</li>
				<li onclick="parent.location='settings'">Change Password</li>
				<li onclick="parent.location='addUser'">Add User</li>
				<li onclick="parent.location='showUsers'">Show Users</li>
				<li onclick="parent.location='addTeam'">Add Team</li>
				<li onclick="parent.location='showTeams'">Show Teams</li>
				<li onclick="parent.location='logout.jsp'">Logout</li>
			</ul>
		</div>
		<div class="col-7 col-m-9">
			<h3 class="text-center">Add Team</h3>
			<hr>
			<br>
			<form id="addTeam" action="addTeam" method="post">
				<div class="row">
					<label for="name" style="color: #0e1526">Enter team name*:</label><br />
					<input id="name" class="input" name="name" type="text" value=""
						size="50" height="15px" /><br />
					<%
						if (null != request.getAttribute("error")) {
							String error = (String) request.getAttribute("error");
							if (error == null){
								error = "";
							}
					%>
					<p style='color:red; font-size:90%;'><%=error%></p>
					<%
						}
					%>
					<br />
				</div>
				<div>
					<label for="manager" style="color: #0e1526">Select
						manager*:</label><br />
					<%
						ArrayList managers = (ArrayList) request.getAttribute("managers");
					%>
					<select name="manager" id="manager" class="dropdown">
						<%
							for (int i = 0; i < managers.size(); i++) {
								String option = (String) managers.get(i);
						%>
						<option value="<%=option%>"><%=option%></option>
						<%
							}
						%>

					</select> <br />
					<br />
				</div>
				<input class="submit_button" name="addTeamButton" type="submit"
					value="Add Team" />
			</form>
		</div>

		<div class="col-3 col-m-12"></div>
	</div>

	<div class="footer">Internal KPI Tool | All rights reserved. 2021</div>
</body>
</html>