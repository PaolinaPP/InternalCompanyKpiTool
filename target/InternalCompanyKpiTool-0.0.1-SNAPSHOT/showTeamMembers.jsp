<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Team Members</title>
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
				<li onclick="parent.location='manager.jsp'">Profile</li>
				<li onclick="parent.location='settings'">Change Password</li>
				<li onclick="parent.location='addGoal'">Add Goal</li>
				<li onclick="parent.location='showTeamMembers'">Show Team Members</li>
				<li onclick="parent.location='showGoals'">Show Goals</li>
				<li onclick="parent.location='showTasks'">Show Tasks</li>
				<li onclick="parent.location='logout.jsp'">Logout</li>
			</ul>


		</div>
		<div class="col-7 col-m-12">
			<div id="box1">
				<div class="container">
					<h3 class="text-center">List of Team Members</h3>
					<hr>
					<br>
					<table class="table table-bordered" id="show-data">
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Role</th>
								<th>Email</th>
								<th>Team</th>
							</tr>
						</thead>
						<tbody>

							<%
								ArrayList teamMembers = (ArrayList) request.getAttribute("teamMembers");
																	for (int i = 0; i < teamMembers.size(); i++) {
																		User user = (User) teamMembers.get(i);
							%>
							<tr>
								<td><%=user.getFirstName()%></td>
								<td><%=user.getLastName()%></td>
								<td><%=user.getRole()%></td>
								<td><%=user.getEmail()%></td>
								<td><%=user.getTeam().getName()%></td>
							</tr>
							<%
								}
							%>
						</tbody>

					</table>
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