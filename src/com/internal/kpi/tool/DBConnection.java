package com.internal.kpi.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class DBConnection {
	public Connection con;
	private PreparedStatement statement;
	public static DBConnection db;

	private DBConnection() {
		DBInfo database = new DBInfo("jdbc:mysql://localhost:3306/", "kpidb?autoReconnect=true&useSSL=false",
				"com.mysql.cj.jdbc.Driver", "root", "12123poli");
		try {
			Class.forName(database.getDriver()).getDeclaredConstructor().newInstance();
			this.con = (Connection) DriverManager.getConnection(database.getUrl() + database.getDbName(),
					database.getUserName(), database.getPassword());
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
	}

	public static DBConnection getInstance() {
		if (db == null)
			db = new DBConnection();
		return db;
	}

	public ResultSet query(String query, List<String> params) throws SQLException {
		statement = db.con.prepareStatement(query);

		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				statement.setString(i + 1, params.get(i));
			}
		}

		ResultSet res = statement.executeQuery();
		return res;
	}

	public int insert(String insertQuery, List<String> params) throws SQLException {
		statement = db.con.prepareStatement(insertQuery);
		for (int i = 0; i < params.size(); i++) {
			statement.setString(i + 1, params.get(i));
		}
		int result = statement.executeUpdate();
		return result;

	}

}