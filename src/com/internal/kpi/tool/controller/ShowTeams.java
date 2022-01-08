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
import com.internal.kpi.tool.model.Team;

@WebServlet(name = "/showTeams", urlPatterns = { "/showTeams" })
public class ShowTeams extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnection conn = DBConnection.getInstance();
	public List<Team> teams = new ArrayList<Team>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		teams.clear();
		String action = request.getServletPath();
		switch (action) {
		default:
			listUsers(request, response);
			break;
		}
	}
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectQuery = "select * from teams";
		ResultSet rs = null;
		try {
			rs = conn.query(selectQuery, null);
			while (rs.next()) {
				Team team = new Team(rs.getInt("id"), rs.getString("name"), rs.getInt("manager_id"));
				team.setManager_email(getManagerEmail(team.getManager_id()));
				teams.add(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("teams", teams);
		RequestDispatcher dispatcher = request.getRequestDispatcher("showTeams.jsp");
		dispatcher.forward(request, response);
	}
	
	private String getManagerEmail(int id) {
		String selectQuery = "select email from users where id=?";
		List<String> queryParams = new ArrayList<String>();
		String email = "";
		ResultSet rs = null;
		queryParams.add(id+"");
		try {
			rs = conn.query(selectQuery, queryParams);
			while (rs.next()) {
				email = rs.getString("email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return email;
	}
}
