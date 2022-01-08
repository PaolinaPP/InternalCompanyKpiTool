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
import com.internal.kpi.tool.model.Team;

@WebServlet(name = "addTeam", urlPatterns = { "/addTeam" })
@MultipartConfig(maxFileSize = 16177215)
public class AddTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	private Team newTeam;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> managers = DatabaseUtil.getAllManagers(conn);
		request.setAttribute("managers", managers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addTeam.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		newTeam = new Team(request.getParameter("name"), DatabaseUtil.getUserId(conn, request.getParameter("manager")));
		if(VerifyData.isValidString(newTeam.getName())) {
			DatabaseUtil.addNewTeam(conn, newTeam);
			response.sendRedirect("addTeam.jsp");
		} else {
			request.setAttribute("error", "Please enter value which includes only letters and spaces.");
			request.setAttribute("managers", DatabaseUtil.getAllManagers(conn));
			RequestDispatcher dispatcher = request.getRequestDispatcher("addTeam.jsp");
			dispatcher.forward(request, response);
		}
	}
}