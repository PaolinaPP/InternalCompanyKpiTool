<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Users</title>
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
		<div class="col-7 col-m-12">
			<div id="box1">
				<div class="container">
					<h3 class="text-center">List of Users</h3>
					<hr>
					<br>
					<table class="table table-bordered" id="show-data">
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Role</th>
								<th>Email</th>
								<th>Permissions</th>
								<th>Team</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>

							<%
								ArrayList users = (ArrayList) request.getAttribute("users");
								for (int i = 0; i < users.size(); i++) {
									User user = (User) users.get(i);
							%>
							<tr>
								<td><%=user.getFirstName()%></td>
								<td><%=user.getLastName()%></td>
								<td><%=user.getRole()%></td>
								<td><%=user.getEmail()%></td>
								<td><%=user.getPermissions()%></td>
								<td><%=user.getTeamName()%></td>
								<td><a href="edit?id=<%=user.getId()%>">Edit</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="deleteUser?id=<%=user.getId()%>">Delete</a></td>
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