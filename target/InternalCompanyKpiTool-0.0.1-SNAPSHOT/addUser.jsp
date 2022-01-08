<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add User</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page import="java.util.ArrayList"%>
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
			<h3 class="text-center">Add User</h3>
			<hr>
			<br>
			<form id="addUser" action="addUser" method="post">
				<div class="row">
					<label for="first_name" style="color: #0e1526">Enter first
						name*:</label><br /> <input id="first_name" class="input"
						name="first_name" type="text" value="" size="50" height="15px" /><br />
					<%
						if (null != request.getAttribute("firstNameError")) {
						String firstNameError = (String) request.getAttribute("firstNameError");
						if (firstNameError == null) {
							firstNameError = "";
						}
					%>
					<p style='color: red; font-size: 90%;'><%=firstNameError%></p>
					<%
						}
					%>
					<br />
				</div>
				<div class="row">
					<label for="last_name" style="color: #0e1526">Enter last
						name*:</label><br /> <input id="lname" class="input" name="last_name"
						type="text" value="" size="50" height="15px" /><br />
					<%
						if (null != request.getAttribute("lastNameError")) {
						String lastNameError = (String) request.getAttribute("lastNameError");
						if (lastNameError == null) {
							lastNameError = "";
						}
					%>
					<p style='color: red; font-size: 90%;'><%=lastNameError%></p>
					<%
						}
					%>
					<br />
				</div>
				<div class="row">
					<label for="role" style="color: #0e1526">Enter role*:</label><br />
					<input id="role" class="input" name="role" type="text" value=""
						size="50" height="15px" /><br />
					<%
						if (null != request.getAttribute("roleError")) {
						String roleError = (String) request.getAttribute("roleError");
						if (roleError == null) {
							roleError = "";
						}
					%>
					<p style='color: red; font-size: 90%;'><%=roleError%></p>
					<%
						}
					%>
					<br />
				</div>
				<div class="row">
					<label for="email" style="color: #0e1526">Enter email*:</label><br />
					<input id="email" class="input" name="email" type="text" value=""
						size="50" /><br />
					<%
						if (null != request.getAttribute("emailError")) {
						String emailError = (String) request.getAttribute("emailError");
						if (emailError == null) {
							emailError = "";
						}
					%>
					<p style='color: red; font-size: 90%;'><%=emailError%></p>
					<%
						}
					%>
					<br />
				</div>
				<div class="row">
					<label for="default_pass" style="color: #0e1526">Enter
						default password (provide it to the user)*:</label><br /> <input
						id="default_pass" class="input" name="default_pass" type="text"
						value="" size="50" /><br />
				</div>
				<%
					if (null != request.getAttribute("passError")) {
					String passError = (String) request.getAttribute("passError");
					if (passError == null) {
						passError = "";
					}
				%>
				<p style='color: red; font-size: 90%;'><%=passError%></p>
				<%
					}
				%>
				<br />
				<div>
					<label for="permissions" style="color: #0e1526">Select user
						permissions*:</label><br /> <select name="permissions" id="permissions"
						class="dropdown">
						<option value="manager">MANAGER</option>
						<option value="team_member">TEAM MEMBER</option>
					</select> <br /> <br />
				</div>
				<div>
					<label for="team" style="color: #0e1526">Select team*:</label><br />
					<%
						ArrayList names = (ArrayList) request.getAttribute("teamNames");
					%>
					<select name="team" id="team" class="dropdown">

						<%
							for (int i = 0; i < names.size(); i++) {
							String option = (String) names.get(i);
						%>
						<option value="<%=option%>"><%=option%></option>
						<%
							}
						%>

					</select> <br /> 
					<%
						if (null != request.getAttribute("secret")) {
							String secret = (String) request.getAttribute("secret");
							if (secret == null) {
								secret = "";
							}
					%>
					<p style='color: blue; font-size: 100%;'><%=secret%></p>
					<%
						}
					%>
					<br />
				</div>
				<input class="submit_button" name="addUserButton" type="submit"
					value="Add User" />
			</form>
		</div>

		<div class="col-3 col-m-12"></div>
	</div>

	<div class="footer">Internal KPI Tool | All rights reserved. 2021</div>
</body>
</html>