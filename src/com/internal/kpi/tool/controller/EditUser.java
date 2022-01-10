package com.internal.kpi.tool.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import com.internal.kpi.tool.model.User;

@WebServlet(name = "edit", urlPatterns = { "/edit" })
@MultipartConfig(maxFileSize = 16177215)
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private User userToEdit;
	private User existingUser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUser() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		String requestId = request.getParameter("id");
		if (requestId != null) {
			try {
				id = Integer.parseInt(requestId.trim());
			} catch (NumberFormatException nbe) {
				nbe.printStackTrace();
			}
		}
		String teamName = null;
		List<String> teamNames = DatabaseUtil.getAllTeams(conn);
		try {
			existingUser= DatabaseUtil.getUser(conn, id);
			teamName = DatabaseUtil.getTeamName(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		request.setAttribute("user", existingUser);
		request.setAttribute("teamNames", teamNames);
		request.setAttribute("teamName", teamName);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		userToEdit = new User(request.getParameter("first_name"), request.getParameter("last_name"),
				request.getParameter("role"), request.getParameter("email"),
				DatabaseUtil.hashPassword(request.getParameter("default_pass")), request.getParameter("permissions"));
		if(!request.getParameter("team").isEmpty()) {
			userToEdit.setTeam(DatabaseUtil.getTeam(conn, request.getParameter("team")));
		}
		checkForUpdates();
		DatabaseUtil.editUser(conn, userToEdit, existingUser);
		DatabaseUtil.editRelationUserTeam(conn, userToEdit, existingUser);
		response.sendRedirect("showUsers");
	}

	private void checkForUpdates() {
		if (userToEdit.getFirstName() == null || userToEdit.getFirstName().isEmpty()) {
			userToEdit.setFirstName(existingUser.getFirstName());
		}
		if (userToEdit.getLastName() == null || userToEdit.getLastName().isEmpty()) {
			userToEdit.setLastName(existingUser.getLastName());
		}
		if (userToEdit.getRole() == null || userToEdit.getRole().isEmpty()) {
			userToEdit.setRole(existingUser.getRole());
		}
		if (userToEdit.getEmail() == null || userToEdit.getEmail().isEmpty()) {
			userToEdit.setEmail(existingUser.getEmail());
		}
		if (userToEdit.getPassword() == null || userToEdit.getPassword().isEmpty()) {
			userToEdit.setPassword(existingUser.getPassword());
		}
		if (userToEdit.getPermissions() == null || userToEdit.getPermissions().isEmpty()) {
			userToEdit.setPermissions(existingUser.getPermissions());
		}
		if (userToEdit.getTeam() == null) {
			userToEdit.setTeam(existingUser.getTeam());
		}
		userToEdit.setSecret_key(existingUser.getSecret_key());
	}
}