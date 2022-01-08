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
import com.internal.kpi.tool.model.User;

@WebServlet(name = "deleteUser", urlPatterns = { "/deleteUser" })
@MultipartConfig(maxFileSize = 16177215)
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private User existingUser;
	private int userId;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUser() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestId = request.getParameter("id");
		if (requestId != null) {
			try {
				userId = Integer.parseInt(requestId.trim());
			} catch (NumberFormatException nbe) {
				nbe.printStackTrace();
			}
		}
		try {
			existingUser = DatabaseUtil.getUser(conn, userId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("deleteUser.jsp");
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
		DatabaseUtil.deleteUserTasks(conn, userId);
		DatabaseUtil.deleteUserGoals(conn, userId);
		DatabaseUtil.deleteUser(conn, userId);
		response.sendRedirect("showUsers");

	}

}
