<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Goal</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page import="java.util.ArrayList"%>
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
			<h3 class="text-center">Add Goal</h3>
			<hr>
			<br>
			<form id="addGoal" action="addGoal" method="post">
				<div class="row">
					<label for="name" style="color: #0e1526">Enter goal name*:</label><br />
					<input id="name" class="input" name="name" type="text" value=""
						size="50" height="15px" /><br /> 
					<%
						if (null != request.getAttribute("errorName")) {
							String errorName = (String) request.getAttribute("errorName");
							if (errorName == null){
								errorName = "";
							}
					%>
					<p style='color:red; font-size:90%;'><%=errorName%></p>
					<%
						}
					%>	
					<br />
				</div>
				<div class="row">
					<label for="description" style="color: #0e1526">Enter goal
						description*:</label><br />
					<textarea id="description" name="description" class="input"
						placeholder="Goal description..." style="height: 200px"></textarea>
					<br /> 
					<%
						if (null != request.getAttribute("errorDescription")) {
							String errorDescription = (String) request.getAttribute("errorDescription");
							if (errorDescription == null){
								errorDescription = "";
							}
					%>
					<p style='color:red; font-size:90%;'><%=errorDescription%></p>
					<%
						}
					%>
					<br />
				</div>
				<div>
					<label for="assign" style="color: #0e1526">Assign goal to*:</label><br />
					<%
						ArrayList emails = (ArrayList) request.getAttribute("teamMembersEmails");
					%>
					<select name="assignTo" id="assignTo" class="dropdown">

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
				<input class="submit_button" name="addGoalButton" type="submit"
					value="Add Goal" />
			</form>
		</div>

		<div class="col-3 col-m-12"></div>
	</div>

	<div class="footer">Internal KPI Tool | All rights reserved. 2021</div>
</body>
</html>