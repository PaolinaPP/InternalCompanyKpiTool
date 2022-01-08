<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.internal.kpi.tool.model.Goal"%>
<%@ page import="com.internal.kpi.tool.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Goal</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
		<div class="col-7 col-m-9">
			<h3 class="text-center">Delete Goal</h3>
			<hr>
			<div id="box1">
				<div id="login">
					<form class="contact_form" method="post" action="deleteGoal">
						<%
							Goal goal = (Goal) request.getAttribute("goal");
															User user = (User) request.getAttribute("user");
						%>
						<div style="color: black;">
							Name:
							<%=goal.getName()%>
							</br> Description:
							<%=goal.getDescription()%>
							</br> Assigned to:
							<%=user.getEmail()%>
							</br> Create on date:
							<%=goal.getDate()%>
							</br> Completed:
							<%=goal.getCompleted()%>
							</br>
						</div>
						<br> <input class="submit_button" name="deleteUserButton"
							type="submit" value="Delete Goal" />
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="footerholder">
		<div class="footer">Internal KPI Tool | All rights reserved.
			2021</div>
	</div>
</body>
</body>
</html>