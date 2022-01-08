<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Teams</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.internal.kpi.tool.model.Team"%>
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
		<div class="col-7 col-m-12">
			<div id="box1">
				<div class="container">
					<h3 class="text-center">List of Teams</h3>
					<hr>
					<br>
					<table class="table table-bordered" id="show-data">
						<thead>
							<tr>
								<th>Name</th>
								<th>Manager</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>

							<%
								ArrayList teams = (ArrayList) request.getAttribute("teams");
								for (int i = 0; i < teams.size(); i++) {
									Team team = (Team) teams.get(i);
							%>
							<tr>
								<td><%=team.getName()%></td>
								<td><%=team.getManager_email()%></td>
								<td><a href="editTeam?id=<%=team.getId()%>">Edit</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="deleteTeams?id=<%=team.getId()%>">Delete</a></td>
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