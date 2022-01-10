<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.internal.kpi.tool.model.User"%>
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
			<h3 class="text-center">Edit User</h3>
			<hr>
			<br>
			<form id="edit" action="edit" method="post">
				<%
					User user = (User) request.getAttribute("user");
				%>
				<div class="row">
					<label for="first_name" style="color: #0e1526">Enter first
						name (Current: <%=user.getFirstName()%>):
					</label><br /> <input id="first_name" class="input" name="first_name"
						type="text" value="" size="50" height="15px" placeholder="<%=user.getFirstName()%>" /><br /> <br />
				</div>
				<div class="row">
					<label for="last_name" style="color: #0e1526">Change last
						name (Current: <%=user.getLastName()%>):
					</label><br /> <input id="lname" class="input" name="last_name"
						type="text" value="" size="50" height="15px" placeholder="<%=user.getLastName()%>"/><br /> <br />
				</div>
				<div class="row">
					<label for="role" style="color: #0e1526">Change role
						(Current: <%=user.getRole()%>):
					</label><br /> <input id="role" class="input" name="role" type="text"
						size="50" height="15px" placeholder="<%=user.getRole()%>"/><br /> <br />
				</div>
				<div class="row">
					<label for="email" style="color: #0e1526">Change email
						(Current: <%=user.getEmail()%>):
					</label><br /> <input id="email" class="input" name="email" type="text"
						size="50" placeholder="<%=user.getEmail()%>"/><br /> <br />
				</div>
				<div class="row">
					<label for="default_pass" style="color: #0e1526">Reset 
						password (provide it to the user)*:</label><br /> <input
						id="default_pass" class="input" name="default_pass" type="text"
						size="50" /><br /> <br />
				</div>
				<div>
					<label for="permissions" style="color: #0e1526">Change
						permissions (Current: <%=user.getPermissions()%>):
					</label><br /> <select name="permissions" id="permissions" class="dropdown">
						<option value=""></option>
						<option value="manager">MANAGER</option>
						<option value="team_member">TEAM MEMBER</option>
					</select> <br /> <br />
				</div>
				<div>
					<%
						ArrayList teamNames = (ArrayList) request.getAttribute("teamNames");
						String teamName = (String) request.getAttribute("teamName");
					%>
					<label for="team" style="color: #0e1526">Change team
						(Current: <%=teamName%>):
					</label><br /> <select name="team" id="team" class="dropdown">
						<option value=""></option>
						<%
							for (int i = 0; i < teamNames.size(); i++) {
							String option = (String) teamNames.get(i);
						%>
						<option value="<%=option%>"><%=option%></option>
						<%
							}
						%>

					</select> <br /> <br />
				</div>
				<input class="submit_button" name="editUserButton" type="submit"
					value="Edit User" />
			</form>
		</div>

		<div class="col-3 col-m-12"></div>
	</div>

	<div class="footer">Internal KPI Tool | All rights reserved. 2021</div>
</body>
</html>