package com.internal.kpi.tool.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internal.kpi.tool.DBConnection;
import com.internal.kpi.tool.DatabaseUtil;
import com.internal.kpi.tool.SecondFactorAuthenticator;
import com.internal.kpi.tool.SecureData;
import com.internal.kpi.tool.model.User;

@WebServlet("/VerifyController")
public class VerifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	public User user;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		String username = (String) request.getSession().getAttribute("http.proxyUser");
		user = DatabaseUtil.getUserByEmail(conn, username);
		if (code.equals(SecondFactorAuthenticator.getTOTPCode(SecureData.decodeEncodedString(user.getSecret_key())))) {
			setupSession(request.getSession());
			redirectUsers(response);
		} else {
			request.setAttribute("error", "Incorrect 2FA code.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void redirectUsers(HttpServletResponse response) throws IOException {
		if (user.getPermissions().equals("manager"))
			response.sendRedirect("manager.jsp");
		else if (user.getPermissions().equals("team_member"))
			response.sendRedirect("teamMember.jsp");
		else if (user.getPermissions().equals("admin"))
			response.sendRedirect("admin.jsp");
	}

	private void setupSession(HttpSession session) {
		session.setAttribute("first_name", user.getFirstName());
		session.setAttribute("last_name", user.getLastName());
		session.setAttribute("role", user.getRole());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("permissions", user.getPermissions());
		session.setMaxInactiveInterval(30 * 60);
	}

}
