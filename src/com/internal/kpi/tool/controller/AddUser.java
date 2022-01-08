package com.internal.kpi.tool.controller;

import java.io.IOException;
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
import com.internal.kpi.tool.model.User;

@WebServlet(name = "addUser", urlPatterns = { "/addUser" })
@MultipartConfig(maxFileSize = 16177215)
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private User userToAdd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> teamNames = DatabaseUtil.getAllTeams(conn);
		request.setAttribute("teamNames", teamNames);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addUser.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String secret = "";
		userToAdd = new User(request.getParameter("first_name"), request.getParameter("last_name"),
				request.getParameter("role"), request.getParameter("email"),
				DatabaseUtil.hashPassword(request.getParameter("default_pass")), request.getParameter("permissions"));
		userToAdd.setTeam(DatabaseUtil.getTeam(conn, request.getParameter("team")));
		if (VerifyData.isValidEmail(userToAdd.getEmail()) && VerifyData.isValidString(userToAdd.getFirstName())
				&& VerifyData.isValidString(userToAdd.getLastName()) && VerifyData.isValidString(userToAdd.getRole())
				&& VerifyData.isValidPassword(request.getParameter("default_pass"))) {
			try {
				secret = DatabaseUtil.addNewUser(conn, userToAdd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DatabaseUtil.addRelationUserTeam(conn, userToAdd);
			request.setAttribute("secret", secret);
		} else {
			if (!VerifyData.isValidEmail(userToAdd.getEmail())) {
				request.setAttribute("emailError", "Email should includes letters and numbers, @ and .");
			}
			if (!VerifyData.isValidString(userToAdd.getFirstName())) {
				request.setAttribute("firstNameError", "First name should includes only letters and spaces.");
			}
			if (!VerifyData.isValidString(userToAdd.getLastName())) {
				request.setAttribute("lastNameError", "Last name should includes only letters and spaces.");
			}
			if (!VerifyData.isValidString(userToAdd.getRole())) {
				request.setAttribute("roleError", "Role should includes only letters and spaces.");
			}
			if (!VerifyData.isValidPassword(request.getParameter("default_pass"))) {
				request.setAttribute("passError", "Password - at least one [0-9], [a-z], [A-Z], [ @#$% ],between 8 and 20 chars.");
			}
		}
		request.setAttribute("teamNames", DatabaseUtil.getAllTeams(conn));
		RequestDispatcher dispatcher = request.getRequestDispatcher("addUser.jsp");
		dispatcher.forward(request, response);
	}
}