<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Tasks</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.internal.kpi.tool.model.Task"%>
</head>
<body>
	<div class="header">Internal KPI Tool</div>
	<div class="row">
		<div class="col-2 col-m-3 menu">
			<ul>
				<%
					String permissions = (String) request.getAttribute("userPermissions");
							if (permissions.equals("manager")) {
				%>
				<li onclick="parent.location='manager.jsp'">Profile</li>
				<li onclick="parent.location='settings'">Change Password</li>
				<li onclick="parent.location='addGoal'">Add Goal</li>
				<li onclick="parent.location='showTeamMembers'">Show Team Members</li>
				<li onclick="parent.location='showGoals'">Show Goals</li>
				<%
					} else if (permissions.equals("team_member")) {
				%>
				<li onclick="parent.location='teamMember.jsp'">Profile</li>
				<li onclick="parent.location='settings'">Change Password</li>
				<li onclick="parent.location='addTask'">Add Task</li>
				<li onclick="parent.location='showGoals'">Show Goals</li>
				<%
					}
				%>
				<li onclick="parent.location='showTasks'">Show Tasks</li>
				<li onclick="parent.location='logout.jsp'">Logout</li>
			</ul>


		</div>
		<div class="col-7 col-m-12">
			<div id="box1">
				<div class="container">
					<h3 class="text-center">List of Tasks</h3>
					<hr>
					<br>
					<table class="table table-bordered" id="show-data">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Goal</th>
								<th>Queue number</th>
								<th>Team</th>
								<th>Task owner</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>

							<%
								ArrayList tasks = (ArrayList) request.getAttribute("tasks");
								for (int i = 0; i < tasks.size(); i++) {
									Task task = (Task) tasks.get(i);
							%>
							<tr>
								<td><%=task.getName()%></td>
								<td><%=task.getDescription()%></td>
								<td><%=task.getGoal().getName()%></td>
								<td><%=task.getQueueNumber()%></td>
								<td><%=task.getTeam().getName()%></td>
								<td><%=task.getOwnerEmail()%></td>
								<td><a href="deleteTask?id=<%=task.getId()%>">Delete</a></td>
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