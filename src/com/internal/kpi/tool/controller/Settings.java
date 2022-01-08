package com.internal.kpi.tool.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.internal.kpi.tool.DBConnection;
import com.internal.kpi.tool.DatabaseUtil;
import com.internal.kpi.tool.VerifyData;

@WebServlet("/settings")

public class Settings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();

	public Settings() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userEmail = (String) request.getSession().getAttribute("http.proxyUser");
		String userPermissions = DatabaseUtil.getUserPermissions(conn, userEmail);
		request.setAttribute("userPermissions", userPermissions);
		RequestDispatcher dispatcher = request.getRequestDispatcher("settings.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = (String) request.getSession().getAttribute("http.proxyUser");
		String oldPwd = request.getParameter("password");
		String newPwd1 = request.getParameter("newPassword1");
		String newPwd2 = request.getParameter("newPassword2");
		String password = null;
		String oldHashedPassword = null;
		String hashedPassword1 = null;
		String hashedPassword2 = null;

		password = DatabaseUtil.getUserPassword(conn, user);
		if (VerifyData.isValidPassword(oldPwd) && VerifyData.isValidPassword(newPwd1)
				&& VerifyData.isValidPassword(newPwd2)) {
			oldHashedPassword = DatabaseUtil.hashPassword(oldPwd);

			if (password.equals(oldHashedPassword)) {
				hashedPassword1 = DatabaseUtil.hashPassword(newPwd1);
				hashedPassword2 = DatabaseUtil.hashPassword(newPwd2);
				if (hashedPassword1.equals(hashedPassword2)) {
					DatabaseUtil.updateUserPassword(conn, user, hashedPassword2, oldHashedPassword);
				}
			} else {
				request.setAttribute("passError",
						"Old password is not correct");
			}

		} else {
			request.setAttribute("passError",
					"Password - at least one [0-9], [a-z], [A-Z], [ @#$% ],between 8 and 20 chars.");
		}
		String userPermissions = DatabaseUtil.getUserPermissions(conn, (String) request.getSession().getAttribute("http.proxyUser"));
		request.setAttribute("userPermissions", userPermissions);
		RequestDispatcher dispatcher = request.getRequestDispatcher("settings.jsp");
		dispatcher.forward(request, response);

	}

}