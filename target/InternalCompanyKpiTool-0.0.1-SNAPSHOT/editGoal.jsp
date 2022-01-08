<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Goal</title>
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
				<li onclick="parent.location='manager.jsp'">Profile</li>
				<li onclick="parent.location='settings'">Change Password</li>
				<li onclick="parent.location='addGoal'">Add Goal</li>
				<li onclick="parent.location='showTeamMembers'">Show Team
					Members</li>
				<li onclick="parent.location='showGoals'">Show Goals</li>
				<li onclick="parent.location='showTasks'">Show Tasks</li>
				<li onclick="parent.location='logout.jsp'">Logout</li>
			</ul>
		</div>
		<div class="col-7 col-m-9">
			<h3 class="text-center">Edit Goal</h3>
			<hr>
			<br>
			<form id="editGoal" action="editGoal" method="post">
				<div class="row">
					<%
						Goal goal = (Goal) request.getAttribute("goal");
					%>
					<label for="name" style="color: #0e1526">Change name
						(Current: <%=goal.getName()%>):
					</label><br /> <input id="name" class="input" name="name" type="text"
						value="" size="50" height="15px" placeholder="<%=goal.getName()%>"/><br /> <br />
				</div>
				<div class="row">
					<label for="description" style="color: #0e1526">Enter goal
						description*:</label><br />
					<textarea id="description" name="description" class="input"
						placeholder="<%=goal.getDescription()%>" style="height: 200px"></textarea>
					<br /><br />
				</div>
				<div>
					<%
						String assignedUserEmail = (String) request.getAttribute("assignedUserEmail");
						ArrayList emails = (ArrayList) request.getAttribute("teamMembersEmails");
					%>
					<label for="assign" style="color: #0e1526">Assign goal to
						(Current: <%=assignedUserEmail%>):
					</label><br /> <select name="assignTo" id="assignTo" class="dropdown">
						<option value=""></option>
						<%
							for (int i = 0; i < emails.size(); i++) {
							String option = (String) emails.get(i);
						%>
						<option value="<%=option%>"><%=option%></option>
						<%
							}
						%>

					</select> <br /> <br />
				</div>
				<div>
					<label for="completion" style="color: #0e1526">Change
						completion (Current: <%=goal.getCompleted()%>):
					</label><br /> <select name="completion" id="completion" class="dropdown">
						<option value="0">Not completed</option>
						<option value="1">Completed</option>
					</select> <br /> <br />
				</div>
				<input class="submit_button" name="editGoalButton" type="submit"
					value="Edit Goal" />
			</form>
		</div>

		<div class="col-3 col-m-12"></div>
	</div>

	<div class="footer">Internal KPI Tool | All rights reserved. 2021</div>
</body>
</html>