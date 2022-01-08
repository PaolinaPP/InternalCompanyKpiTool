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
import com.internal.kpi.tool.model.Goal;

@WebServlet(name = "addGoal", urlPatterns = { "/addGoal" })
@MultipartConfig(maxFileSize = 16177215)
public class AddGoal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private String userEmail;
	private Goal goal;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> teamMembersEmails = new ArrayList<String>();
		userEmail = (String) request.getSession().getAttribute("http.proxyUser");	
		int userId = DatabaseUtil.getUserId(conn, userEmail);
		teamMembersEmails = DatabaseUtil.getAllTeamMembers(conn, userId);
		request.setAttribute("teamMembersEmails", teamMembersEmails);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addGoal.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		goal = new Goal(request.getParameter("name"), request.getParameter("description"),
				DatabaseUtil.getUserId(conn, request.getParameter("assignTo")), DatabaseUtil.getUserId(conn, userEmail), "", 0);
		if (VerifyData.isValidString(goal.getName()) && VerifyData.isValidText(goal.getDescription())) {
			DatabaseUtil.addNewGoal(conn, goal);
			response.sendRedirect("addGoal");
		} else {
			if (!VerifyData.isValidString(goal.getName())) {
				request.setAttribute("errorName", "Goal name should includes only letters and spaces.");
			}
			if (!VerifyData.isValidText(goal.getDescription())) {
				request.setAttribute("errorDescription", "Goal description should includes only letters, numbers and spaces.");
			}
			request.setAttribute("teamMembersEmails", DatabaseUtil.getAllTeamMembers(conn, DatabaseUtil.getUserId(conn, userEmail)));
			RequestDispatcher dispatcher = request.getRequestDispatcher("addGoal.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}
