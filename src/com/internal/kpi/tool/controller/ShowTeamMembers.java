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
import com.internal.kpi.tool.model.User;

@WebServlet(name = "/showTeamMembers", urlPatterns = { "/showTeamMembers" })
public class ShowTeamMembers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	public List<User> users = new ArrayList<User>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		users.clear();
		String userEmail = (String) request.getSession().getAttribute("http.proxyUser");
		int userId = DatabaseUtil.getUserId(conn, userEmail);
		String action = request.getServletPath();
		switch (action) {
		default:
			listUsers(request, response, userId);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response, int userId)
			throws ServletException, IOException {
		List<String> queryParams = new ArrayList<String>();
		String selectQuery = "SELECT users.* FROM user_teams JOIN users ON users.id= user_teams.user_id JOIN teams ON teams.id= user_teams.team_id WHERE teams.manager_id= ?";
		;
		queryParams.add(Integer.toString(userId));
		ResultSet rs = null;
		try {
			rs = conn.query(selectQuery, queryParams);
			while (rs.next()) {
				User usr = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("role"), rs.getString("email"), rs.getString("pass"), rs.getString("permissions"),
						rs.getString("secret_key"));
				usr.setTeam(DatabaseUtil.getTeam(conn, usr.getId()));
				users.add(usr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("teamMembers", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("showTeamMembers.jsp");
		dispatcher.forward(request, response);
	}
}
