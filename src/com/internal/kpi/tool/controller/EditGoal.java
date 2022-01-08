
package com.internal.kpi.tool.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import com.internal.kpi.tool.model.Goal;

@WebServlet(name = "editGoal", urlPatterns = { "/editGoal" })
@MultipartConfig(maxFileSize = 16177215)
public class EditGoal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private int goalId;
	private Goal goalToEdit;
	private Goal existingGoal;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditGoal() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> teamMembersEmails = new ArrayList<String>();
		String requestId = request.getParameter("id");
		if (requestId != null) {
			try {
				goalId = Integer.parseInt(requestId.trim());
				String userEmail = (String) request.getSession().getAttribute("http.proxyUser");
				int userId = DatabaseUtil.getUserId(conn, userEmail);
				existingGoal = DatabaseUtil.getGoal(conn, goalId);
				String assignedUserEmail = DatabaseUtil.getUserEmail(conn, existingGoal.getAssignToUserId());
				teamMembersEmails = DatabaseUtil.getAllTeamMembers(conn, userId);
				RequestDispatcher dispatcher = request.getRequestDispatcher("editGoal.jsp");
				request.setAttribute("goal", existingGoal);
				request.setAttribute("teamMembersEmails", teamMembersEmails);
				request.setAttribute("assignedUserEmail", assignedUserEmail);
				dispatcher.forward(request, response);
			} catch (NumberFormatException | SQLException nbe) {
				nbe.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		goalToEdit = new Goal(request.getParameter("name"), request.getParameter("description"),
				DatabaseUtil.getUserId(conn, request.getParameter("assignTo")), 0, "",
				Integer.parseInt(request.getParameter("completion")));
		checkForUpdates();
		DatabaseUtil.updateGoal(conn, goalToEdit, existingGoal);
		response.sendRedirect("showGoals");
	}

	private void checkForUpdates() {
		if (goalToEdit.getName() == null || goalToEdit.getName().isEmpty()) {
			goalToEdit.setName(existingGoal.getName());
		}
		if (goalToEdit.getDescription() == null || goalToEdit.getDescription().isEmpty()) {
			goalToEdit.setDescription(existingGoal.getDescription());
		}
		if (goalToEdit.getAssignToUserId() == 0) {
			goalToEdit.setAssignToUserId(existingGoal.getAssignToUserId());
		}
		if (goalToEdit.getCreatedByUserId() == 0) {
			goalToEdit.setCreatedByUserId(existingGoal.getCreatedByUserId());
		}
		if (goalToEdit.getDate() == null || goalToEdit.getDate().isEmpty()) {
			goalToEdit.setDate(existingGoal.getDate());
		}
		if (goalToEdit.getCompleted() == 0) {
			goalToEdit.setCompleted(existingGoal.getCompleted());
		}
	}

}