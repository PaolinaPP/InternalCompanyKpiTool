package com.internal.kpi.tool.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.internal.kpi.tool.DBConnection;
import com.internal.kpi.tool.DatabaseUtil;
import com.internal.kpi.tool.VerifyData;
import com.internal.kpi.tool.model.User;

@WebServlet(name = "/login", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private String username = "";
	private User user;

	public String getUsername() {
		return username;
	}

	public Login() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		username = request.getParameter("username");
		String pass = request.getParameter("password");

		if (VerifyData.isValidEmail(username) && VerifyData.isValidPassword(request.getParameter("password"))) {
			user = DatabaseUtil.configUser(conn, username);
			String hashedPassword = DatabaseUtil.hashPassword(pass);
			if (user != null && user.getPassword().equals(hashedPassword)) {
				Cookie userName = new Cookie("user", username);
				userName.setMaxAge(30 * 60);
				response.addCookie(userName);
				request.getSession().setAttribute("http.proxyUser", username);
				request.getSession().setAttribute("http.proxyPassword", hashedPassword);
				response.sendRedirect("auth.jsp");
			} else {
				request.setAttribute("error", "Incorrect password");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("error", "Incorrect email or password.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}