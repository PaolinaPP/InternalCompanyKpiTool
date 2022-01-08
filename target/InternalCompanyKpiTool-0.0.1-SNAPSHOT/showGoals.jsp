<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Goals</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.internal.kpi.tool.model.Goal"%>
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
				<li onclick="parent.location='showTeamMembers'">Show Team
					Members</li>
				<%
					} else if (permissions.equals("team_member")) {
				%>
				<li onclick="parent.location='teamMember.jsp'">Profile</li>
				<li onclick="parent.location='settings'">Change Password</li>
				<li onclick="parent.location='addTask'">Add Task</li>
				<%
					}
				%>
				<li onclick="parent.location='showGoals'">Show Goals</li>
				<li onclick="parent.location='showTasks'">Show Tasks</li>
				<li onclick="parent.location='logout.jsp'">Logout</li>
			</ul>


		</div>
		<div class="col-7 col-m-12">
			<div id="box1">
				<div class="container">
					<h3 class="text-center">List of Goals</h3>
					<hr>
					<br>
					<table class="table table-bordered" id="show-data">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Assign To</th>
								<th>Date created</th>
								<th>Completed</th>
								<%
									if (permissions.equals("manager")) {
								%>
								<th>Actions</th>
								<%
									}
								%>
							</tr>
						</thead>
						<tbody>

							<%
								ArrayList goals = (ArrayList) request.getAttribute("goals");
													for (int i = 0; i < goals.size(); i++) {
														Goal goal = (Goal) goals.get(i);
							%>
							<tr>
								<td><%=goal.getName()%></td>
								<td><%=goal.getDescription()%></td>
								<td><%=goal.getAssignToUserEmail()%></td>
								<td><%=goal.getDate()%></td>
								<%
									if (goal.getCompleted()==1) {
								%>
								<td>True</td>
								<%
									}else {
								%>
								<td>False</td>
								<%
									}
								%>
								<%
									if (permissions.equals("manager")) {
								%>
								<td><a href="editGoal?id=<%=goal.getId()%>">Edit</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="deleteGoal?id=<%=goal.getId()%>">Delete</a></td>
								<%
									}
								%>
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