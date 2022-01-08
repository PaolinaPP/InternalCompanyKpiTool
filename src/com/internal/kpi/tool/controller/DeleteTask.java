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
import com.internal.kpi.tool.model.Task;
import com.internal.kpi.tool.model.User;

@WebServlet(name = "deleteTask", urlPatterns = { "/deleteTask" })
@MultipartConfig(maxFileSize = 16177215)
public class DeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private Task task;
	private int taskId;
	private User user;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userEmail = (String) request.getSession().getAttribute("http.proxyUser");
		user = DatabaseUtil.getUserByEmail(conn,userEmail);
		request.setAttribute("userPermissions", user.getPermissions());
		String requestId = request.getParameter("id");
		if (requestId != null) {
			try {
				taskId = Integer.parseInt(requestId.trim());
			} catch (NumberFormatException nbe) {
				nbe.printStackTrace();
			}
		}
		try {
			task = DatabaseUtil.getTask(conn, taskId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("deleteTask.jsp");
			request.setAttribute("task", task);
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
		DatabaseUtil.deleteTask(conn, taskId);
		response.sendRedirect("showTasks");
	}
}
