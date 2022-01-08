package com.internal.kpi.tool.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.internal.kpi.tool.DBConnection;
import com.internal.kpi.tool.DatabaseUtil;
import com.internal.kpi.tool.model.Goal;
import com.internal.kpi.tool.model.User;

@WebServlet(name = "deleteGoal", urlPatterns = { "/deleteGoal" })
@MultipartConfig(maxFileSize = 16177215)
public class DeleteGoal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private Goal goal;
	private int goalId;
	private User existingUser;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestId = request.getParameter("id");
		if (requestId != null) {
			try {
				goalId = Integer.parseInt(requestId.trim());
			} catch (NumberFormatException nbe) {
				nbe.printStackTrace();
			}
		}
		try {
			goal = DatabaseUtil.getGoal(conn, goalId);
			existingUser = DatabaseUtil.getUser(conn, goal.getAssignToUserId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("deleteGoal.jsp");
			request.setAttribute("goal", goal);
			request.setAttribute("user", existingUser);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatabaseUtil.deleteGoal(conn, goalId);
		response.sendRedirect("showGoals");
	}

}
