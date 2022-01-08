<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change password</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="header">Internal KPI Tool</div>
	<div class="row">
		<div class="col-2 col-m-3 menu">
			<ul>
				<%
					String permissions = (String) request.getAttribute("userPermissions");
					if (permissions.equals("admin")) {
				%>
				<li onclick="parent.location='admin.jsp'">Profile</li>
				<li onclick="parent.location='settings'">Change Password</li>
				<li onclick="parent.location='addUser'">Add User</li>
				<li onclick="parent.location='showUsers'">Show Users</li>
				<li onclick="parent.location='addTeam'">Add Team</li>
				<li onclick="parent.location='showTeams'">Show Teams</li>
				<%
					} else if (permissions.equals("manager")) {
				%>
				<li onclick="parent.location='manager.jsp'">Profile</li>
				<li onclick="parent.location='settings'">Change Password</li>
				<li onclick="parent.location='addGoal'">Add Goal</li>
				<li onclick="parent.location='showTeamMembers'">Show Team Members</li>
				<li onclick="parent.location='showGoals'">Show Goals</li>
				<li onclick="parent.location='showTasks'">Show Tasks</li>
				<%
					} else if (permissions.equals("team_member")) {
				%>
				<li onclick="parent.location='teamMember.jsp'">Profile</li>
				<li onclick="parent.location='settings'">Change Password</li>
				<li onclick="parent.location='addTask'">Add Task</li>
				<li onclick="parent.location='showGoals'">Show Goals</li>
				<li onclick="parent.location='showTasks'">Show Tasks</li>
				<%
					}
				%>
				<li onclick="parent.location='logout.jsp'">Logout</li>
			</ul>
		</div>
		<div class="col-7 col-m-9">
			<h3 class="text-center">Change Password</h3>
			<hr>
			<br>
			<div id="box1">
				<div id="login">
					<form class="contact_form" action="settings" method="post">
						<div class="row">
							<label for="first_name" style="color: #0e1526">Enter your
								current password*:</label>
							<br /> <input class="input" name="password" type="password"
								value="" size="50" /><br /> <br />
						</div>
						<div class="row">
							<label for="first_name" style="color: #0e1526">Enter your
								new password*:</label>
							<br /> <input class="input" name="newPassword1" type="password"
								value="" size="50" /><br /> <br />
						</div>
						<div class="row">
							<label for="first_name" style="color: #0e1526">Repeat your
								new password*:</label>
							<br /> <input class="input" name="newPassword2" type="password"
								value="" size="50" /><br /> <br />
						</div>
						<input class="submit_button" type="submit" value="Change Password" /> <br />
					</form>
				</div>
			</div>
		</div>
		<div class="col-3 col-m-12"></div>
	</div>

	<div class="footerholder">
		<div class="footer">Internal KPI Tool | All rights reserved.
			2021</div>
	</div>

</body>
</html>