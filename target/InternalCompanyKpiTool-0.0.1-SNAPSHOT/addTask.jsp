<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Task</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page import="java.util.ArrayList"%>
</head>
<body>
	<div class="header">Internal KPI Tool</div>
	<div class="row">
		<div class="col-2 col-m-3 menu">
			<ul>
				<li onclick="parent.location='teamMember'">Profile</li>
				<li onclick="parent.location='settings'">Change Password</li>
				<li onclick="parent.location='addTask'">Add Task</li>
				<li onclick="parent.location='showGoals'">Show Goals</li>
				<li onclick="parent.location='showTasks'">Show Tasks</li>
				<li onclick="parent.location='logout.jsp'">Logout</li>
			</ul>
		</div>
		<div class="col-7 col-m-9">
			<h3 class="text-center">Add Task</h3>
			<hr>
			<br>
			<form id="addTask" action="addTask" method="post">
				<div class="row">
					<label for="name" style="color: #0e1526">Enter task name*:</label><br />
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
					<label for="description" style="color: #0e1526">Enter task
						description*:</label><br />
					<textarea id="description" name="description" class="input"
						placeholder="Task description..." style="height: 200px"></textarea>
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
					<label for="assign" style="color: #0e1526">Assign 
						task to goal*:</label><br />
					<%
						ArrayList goals = (ArrayList) request.getAttribute("goals");
					%>
					<select name="assignToGoal" id="assignToGoal" class="dropdown">

						<%
							for (int i = 0; i < goals.size(); i++) {
							String option = (String) goals.get(i);
						%>
						<option value="<%=option%>"><%=option%></option>
						<%
							}
						%>

					</select> <br /> <br />
				</div>
				<div>
					<label for="q" style="color: #0e1526">Enter Q id*:</label><br />
					<select name="q" id="q" class="dropdown">
						<option value="Q1">Q1</option>
						<option value="Q2">Q2</option>
						<option value="Q3">Q3</option>
						<option value="Q4">Q4</option>
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