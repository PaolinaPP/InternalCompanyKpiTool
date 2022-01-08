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
import com.internal.kpi.tool.model.Task;
import com.internal.kpi.tool.model.User;

@WebServlet(name = "/showTasks", urlPatterns = { "/showTasks" })
public class ShowTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private List<Task> tasks = new ArrayList<Task>();
	private User user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		tasks.clear();
		String userEmail = (String) request.getSession().getAttribute("http.proxyUser");
		getUser(userEmail);
		request.setAttribute("userPermissions", user.getPermissions());
		if (user.getPermissions().equals("manager")) {
			listTasksManager(request, response, user.getId());
		} else if (user.getPermissions().equals("team_member")) {
			listTasksTeamMember(request, response, user.getId());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private void getUser(String email) {
		String selectQuery = "select * from users where email=?";
		List<String> queryParams = new ArrayList<String>();
		ResultSet rs = null;
		queryParams.add(email);
		try {
			rs = conn.query(selectQuery, queryParams);
			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("role"), rs.getString("email"), rs.getString("pass"), rs.getString("permissions"),
						rs.getString("secret_key"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void listTasksTeamMember(HttpServletRequest request, HttpServletResponse response, int userId)
			throws ServletException, IOException {
		List<String> queryParams = new ArrayList<String>();
		String selectQuery = "select * from tasks where owner_id=?";
		queryParams.add(Integer.toString(userId));
		ResultSet rs = null;
		try {
			rs = conn.query(selectQuery, queryParams);
			while (rs.next()) {
				Task task = new Task(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("owner_id"), DatabaseUtil.getGoal(conn, rs.getInt("goal_id")), rs.getString("queue_number"),
						DatabaseUtil.getTeamById(conn, rs.getInt("team_id")));
				task.setOwnerEmail(DatabaseUtil.getUser(conn, task.getOwnerId()).getEmail());
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("tasks", tasks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("showTasks.jsp");
		dispatcher.forward(request, response);
	}

	private void listTasksManager(HttpServletRequest request, HttpServletResponse response, int userId)
			throws ServletException, IOException {
		List<String> queryParams = new ArrayList<String>();
		String selectQuery = "SELECT * FROM tasks WHERE team_id IN (SELECT id FROM teams WHERE manager_id = ?)";
		queryParams.add(Integer.toString(userId));
		ResultSet rs = null;
		try {
			rs = conn.query(selectQuery, queryParams);
			while (rs.next()) {
				Task task = new Task(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("owner_id"), DatabaseUtil.getGoal(conn, rs.getInt("goal_id")), rs.getString("queue_number"),
						DatabaseUtil.getTeamById(conn, rs.getInt("team_id")));
				task.setOwnerEmail(DatabaseUtil.getUser(conn, task.getOwnerId()).getEmail());
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("tasks", tasks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("showTasks.jsp");
		dispatcher.forward(request, response);
	}

}
