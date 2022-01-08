package com.internal.kpi.tool.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.internal.kpi.tool.DBConnection;
import com.internal.kpi.tool.DatabaseUtil;
import com.internal.kpi.tool.model.Goal;
import com.internal.kpi.tool.model.User;

@WebServlet(name = "/showGoals", urlPatterns = { "/showGoals" })
public class ShowGoals extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConnection conn = DBConnection.getInstance();
	private List<Goal> goals = new ArrayList<Goal>();
	private User user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		goals.clear();
		String userEmail = (String) request.getSession().getAttribute("http.proxyUser");
		user = DatabaseUtil.getUserByEmail(conn, userEmail);
		request.setAttribute("userPermissions", user.getPermissions());
		if (user.getPermissions().equals("manager")) {
			listGoalsManager(request, response);
		} else if (user.getPermissions().equals("team_member")) {
			listGoalsTeamMember(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private void listGoalsManager(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> queryParams = new ArrayList<String>();
		String selectQuery = "select * from goals where created_by_user_id=?";
		queryParams.add(Integer.toString(user.getId()));
		ResultSet rs = null;
		try {
			rs = conn.query(selectQuery, queryParams);
			while (rs.next()) {
				Goal goal = new Goal(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("assigned_to_user_id"), rs.getInt("created_by_user_id"),
						rs.getString("created_on_date"), rs.getInt("completed"));
				goal.setAssignToUserEmail(DatabaseUtil.getUserEmail(conn, (goal.getAssignToUserId())));
				goals.add(goal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("goals", goals);
		RequestDispatcher dispatcher = request.getRequestDispatcher("showGoals.jsp");
		dispatcher.forward(request, response);
	}

	private void listGoalsTeamMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> queryParams = new ArrayList<String>();
		String selectQuery = "select * from goals where assigned_to_user_id=?";
		queryParams.add(Integer.toString(user.getId()));
		ResultSet rs = null;
		try {
			rs = conn.query(selectQuery, queryParams);
			while (rs.next()) {
				Goal goal = new Goal(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("assigned_to_user_id"), rs.getInt("created_by_user_id"),
						rs.getString("created_on_date"), rs.getInt("completed"));
				goal.setAssignToUserEmail(DatabaseUtil.getUserEmail(conn, (goal.getAssignToUserId())));
				goals.add(goal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("goals", goals);
		RequestDispatcher dispatcher = request.getRequestDispatcher("showGoals.jsp");
		dispatcher.forward(request, response);
	}

}
