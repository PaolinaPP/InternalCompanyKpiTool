package com.internal.kpi.tool.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.internal.kpi.tool.DBConnection;
import com.internal.kpi.tool.DatabaseUtil;
import com.internal.kpi.tool.VerifyData;
import com.internal.kpi.tool.model.Task;

@WebServlet(name = "addTask", urlPatterns = { "/addTask" })
@MultipartConfig(maxFileSize = 16177215)
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private String userEmail;
	private Task task;
	private int userId;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> goals = new ArrayList<String>();
		userEmail = (String) request.getSession().getAttribute("http.proxyUser");
		userId = DatabaseUtil.getUserId(conn, userEmail);
		goals = DatabaseUtil.getAllGoals(conn, userId);
		request.setAttribute("goals", goals);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addTask.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		task = new Task(request.getParameter("name"), request.getParameter("description"), DatabaseUtil.getUserId(conn, userEmail),
				DatabaseUtil.getGoalAssignedToSpecificUser(conn, userId, request.getParameter("assignToGoal")), request.getParameter("q"));
		if (VerifyData.isValidString(task.getName()) && VerifyData.isValidText(task.getDescription())) {
			task.setTeam(DatabaseUtil.getTeam(conn, task.getOwnerId()));
			DatabaseUtil.addNewTask(conn, task);
			response.sendRedirect("addTask");
		} else {
			if (!VerifyData.isValidString(task.getName())) {
				request.setAttribute("errorName", "Task name should includes only letters and spaces.");
			}
			if (!VerifyData.isValidText(task.getDescription())) {
				request.setAttribute("errorDescription", "Task description should includes only letters, numbers and spaces.");
			}
			request.setAttribute("goals", DatabaseUtil.getAllGoals(conn, userId));
			RequestDispatcher dispatcher = request.getRequestDispatcher("addTask.jsp");
			dispatcher.forward(request, response);
		}
	}

}
